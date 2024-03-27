package ru.newsystems.nispro_bot.telegram.handler.update.messageVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.dto.MessageGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.callback.DownloadFilesDTO;
import ru.newsystems.nispro_bot.base.model.dto.callback.SendDataDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketSearchDTO;
import ru.newsystems.nispro_bot.base.model.state.ErrorState;
import ru.newsystems.nispro_bot.base.model.state.MessageState;
import ru.newsystems.nispro_bot.base.model.state.ReplyKeyboardButton;
import ru.newsystems.nispro_bot.base.repo.local.MessageLocalRepo;
import ru.newsystems.nispro_bot.base.utils.StringUtil;
import ru.newsystems.nispro_bot.telegram.handler.message.MessageHandler;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.newsystems.nispro_bot.base.utils.NumberUtil.getIdByTicketNumber;
import static ru.newsystems.nispro_bot.telegram.utils.Messages.prepareTextArticle;
import static ru.newsystems.nispro_bot.telegram.utils.Messages.prepareTextTicket;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.*;

@Component
public class FullVersion implements Version {

    private final RestNISService restNISService;
    private final MessageLocalRepo localRepo;
    private final VirtaBot bot;
    private final TelegramBotRegistrationService service;

    @Autowired
    private List<MessageHandler> messageHandlers;

    public FullVersion(RestNISService restNISService, MessageLocalRepo localRepo, VirtaBot bot, TelegramBotRegistrationService service) {
        this.restNISService = restNISService;
        this.localRepo = localRepo;
        this.bot = bot;
        this.service = service;
    }

    @Override
    public boolean handle(Update update, TelegramBotRegistration registration) throws TelegramApiException {
//        if (registration.isLightVersion() || update.getMessage().getChatId() < 0) return false;
        if (update.getMessage().getChatId() < 0) return false;

        bot.execute(SendChatAction.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .action(ActionType.TYPING.toString())
                .build());
        return handleText(update, registration);
    }

    private boolean handleText(Update update, TelegramBotRegistration registration) throws TelegramApiException {
        String text = update.getMessage().getText();
        long tk = getIdByTicketNumber(text);
        Optional<TicketSearchDTO> ticketSearch =
                tk != 0 ? restNISService.getTicketOperationSearch(List.of(tk), update.getMessage()
                        .getChatId()) : Optional.empty();
        if (ticketSearch.isPresent()) {
            return getDataByTicketID(update, text, ticketSearch);
        } else {
            boolean isRedirect = update.getMessage().getForwardFrom() != null;
            if (!registration.isLightVersion()) {
                if (prepareFunctionalMsg(update, isRedirect)) return true;
            }
            if (checkInnerAttrFiles(update, ticketSearch)) return false;
            boolean isExist = service.getByAgentIdTelegram(String.valueOf(update.getMessage()
                    .getChatId()));
            if (!isExist) {
                sendExceptionMsg(update, text, "tk", bot);
            } else {
                if (prepareFunctionalMsg(update, true)) return true;
            }
        }
        return false;
    }

    private boolean checkInnerAttrFiles(Update update, Optional<TicketSearchDTO> ticketSearch) throws TelegramApiException {
        if (update.getMessage().hasPhoto()) {
            return true;
        }
        if (ticketSearch.isPresent() &&
                ticketSearch.get().getError().getErrorCode().equals(ErrorState.NOT_AUTHORIZED.getCode())) {
            missingRegistration(update.getMessage(), bot);
            return true;
        }
        return false;
    }

    private boolean prepareFunctionalMsg(Update update, boolean isRedirect) {
        for (MessageHandler messageHandler : messageHandlers) {
            try {
                if (messageHandler.handleUpdate(update, isRedirect)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean getDataByTicketID(Update update, String text, Optional<TicketSearchDTO> ticketSearch) throws TelegramApiException {
        List<Long> ticketsId = ticketSearch.get().getTicketIDs();
        Optional<TicketGetDTO> ticket = restNISService.getTicketOperationGet(ticketsId, update.getMessage()
                .getChatId());
        if (ticket.isPresent()) {
            if (ticket.get().getError() == null) {
                sendTicketTextMsg(update, ticket.get().getTickets().get(0));
                updateLocalRepo(update, ticket);
                return true;
            } else {
                if (ticket.get().getError() != null &&
                        ticket.get().getError().getErrorCode().equals(ErrorState.NOT_AUTHORIZED.getMsg())) {
                    missingRegistration(update.getMessage(), bot);
                    return false;
                }
                sendErrorMsg(bot, update, text, ticket.get().getError());
                return false;
            }
        } else {
            sendExceptionMsg(update, text, "id", bot);
            return false;
        }
    }

    private void sendTicketTextMsg(Update update, TicketJ ticket) throws TelegramApiException {
        String resultText = prepareText(ticket);
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(List.of(InlineKeyboardButton.builder()
                .text(ReplyKeyboardButton.COMMENT.getLabel() + " Отправить комментарий")
                .callbackData(StringUtil.serialize(new SendDataDTO(ticket.getTicketNumber())))
                .build()));
        if (ticket.getArticles().get(ticket.getArticles().size() - 1).getAttachments() != null) {
            buttons.add(List.of(InlineKeyboardButton.builder()
                    .text(ReplyKeyboardButton.DOWNLOAD.getLabel() + " Выгрузить документы")
                    .callbackData(StringUtil.serialize(new DownloadFilesDTO(update.getMessage()
                            .getChatId(), ticket.getTicketNumber(), 0L, "s")))
                    .build()));
        }

        bot.execute(SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text(resultText)
                .parseMode(ParseMode.HTML)
                .protectContent(true)
                .replyToMessageId(update.getMessage().getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build());
    }

    private String prepareText(TicketJ ticket) {
        String ticketText = prepareTextTicket(ticket);
        String countTMsgText = ticketText + "\n<i>Количество комментариев:</i> " + ticket.getArticles().size();
        Article lastArticle = ticket.getArticles().get(ticket.getArticles().size() - 1);
        int sizeAttach = lastArticle.getAttachments() == null ? 0 : lastArticle.getAttachments().size();
        String lastComment = countTMsgText + "\n\n<b>Последний комментарий:</b>";
        return prepareTextArticle(lastArticle, sizeAttach, lastComment);
    }

    private void updateLocalRepo(Update update, Optional<TicketGetDTO> ticket) {
        MessageGetDTO messageGetDTO = new MessageGetDTO();
        messageGetDTO.setTicket(ticket.get().getTickets().get(0));
        messageGetDTO.setState(MessageState.SHOW);
        localRepo.update(update.getMessage().getChatId(), messageGetDTO);
    }

}
