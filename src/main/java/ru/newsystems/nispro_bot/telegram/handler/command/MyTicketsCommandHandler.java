package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketSearchDTO;
import ru.newsystems.nispro_bot.base.model.state.Command;
import ru.newsystems.nispro_bot.base.model.state.ErrorState;
import ru.newsystems.nispro_bot.config.cache.CacheStore;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;

import java.util.List;
import java.util.Optional;

import static ru.newsystems.nispro_bot.telegram.utils.Button.prepareButtonsFromTickets;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.missingRegistration;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.resultOperationToChat;


@Component
public class MyTicketsCommandHandler implements CommandHandler {

    private final RestNISService restNISService;
    private final VirtaBot bot;
    private final CacheStore<TicketGetDTO> cache;

    public MyTicketsCommandHandler(RestNISService restNISService, VirtaBot bot, CacheStore<TicketGetDTO> cache) {
        this.restNISService = restNISService;
        this.bot = bot;
        this.cache = cache;
    }

    @Override
    public void handleCommand(Message message, String text, TelegramBotRegistration registration) throws TelegramApiException {
        Optional<TicketSearchDTO> ticketOperationSearch = restNISService.getTicketOperationSearch(message.getChatId());

        if (ticketOperationSearch.isPresent() && ticketOperationSearch.get().getTicketIDs() != null &&
                ticketOperationSearch.get().getTicketIDs().size() > 0) {

            bot.execute(SendChatAction.builder()
                    .chatId(String.valueOf(message.getChatId()))
                    .action(ActionType.TYPING.toString())
                    .build());

            List<Long> ticketIDs = ticketOperationSearch.get().getTicketIDs();
            Optional<TicketGetDTO> ticketOperationGet =
                    restNISService.getTicketOperationGet(ticketIDs, message.getChatId());
            if (ticketOperationGet.get().getError() != null &&
                    ticketOperationGet.get().getError().getErrorCode().equals(ErrorState.NOT_AUTHORIZED.getCode())) {
                missingRegistration(message, bot);

            } else {
                TicketGetDTO value = ticketOperationGet.get();
                cache.update(message.getChatId(), value);
                List<List<InlineKeyboardButton>> inlineKeyboard =
                        prepareButtonsFromTickets(value.getTickets(), 1, value.getTickets().size(), registration.getLogin());

                bot.execute(SendMessage.builder()
                        .chatId(String.valueOf(message.getChatId()))
                        .text("<pre>Количество открытых заявок: <b>" + ticketIDs.size() + "</b></pre>")
                        .parseMode(ParseMode.HTML)
                        .protectContent(true)
                        .replyToMessageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(inlineKeyboard).build())
                        .build());
            }

        } else {
            if (ticketOperationSearch.get().getError() != null &&
                    ticketOperationSearch.get().getError().getErrorCode().equals(ErrorState.NOT_AUTHORIZED.getCode())) {
                missingRegistration(message, bot);
            } else {
                resultOperationToChat(message, bot, false);
            }
        }
    }

    @Override
    public Command getCommand() {
        return Command.MY_TICKET;
    }

}
