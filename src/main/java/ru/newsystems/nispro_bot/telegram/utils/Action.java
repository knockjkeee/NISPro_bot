package ru.newsystems.nispro_bot.telegram.utils;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketUpdateCreateDTO;
import ru.newsystems.nispro_bot.base.model.state.ErrorState;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;

import java.util.Optional;

import static ru.newsystems.nispro_bot.telegram.utils.Notification.*;


public class Action {
    public static Message getMessage(Update update) {
        if (!update.hasMessage()) {
            return null;
        }
        Message message = update.getMessage();
        if (!message.hasText() && !message.hasPhoto()) {
            if (!message.hasDocument()) {
                return null;
            }
        }
        return message;
    }

    public static void sendNewComment(Update update, RequestDataDTO req, RestNISService restNISService, VirtaBot bot) throws TelegramApiException {
        Optional<TicketUpdateCreateDTO> ticketOperationUpdate =
                restNISService.getTicketOperationUpdate(req, update.getMessage().getChatId(),
                        update.getMessage().getFrom().getFirstName() + "/" +
                                update.getMessage().getFrom().getUserName());
        if (ticketOperationUpdate.isPresent() && ticketOperationUpdate.get().getError() == null) {
            resultOperationToChat(update, bot, true);
        } else {
            if (ticketOperationUpdate.get().getError() != null &&
                    ticketOperationUpdate.get().getError().getErrorCode().equals(ErrorState.NOT_AUTHORIZED.getCode())) {
                missingRegistration(update.getMessage(), bot);
            } else {
                sendErrorMsg(bot, update, update.getMessage().getReplyToMessage().getText(), ticketOperationUpdate.get()
                        .getError());
            }
        }
    }

    public static void sendCreateTicket(Update update, RequestDataDTO req, RestNISService restNISService, VirtaBot bot, TelegramBotRegistration registration) throws TelegramApiException {
        Optional<TicketUpdateCreateDTO> ticketOperationUpdate =
                restNISService.getTicketOperationCreate(req, update.getMessage().getChatId(),
                        update.getMessage().getFrom().getFirstName() + "/" +
                                update.getMessage().getFrom().getUserName(), registration);
        if (ticketOperationUpdate.isPresent() && ticketOperationUpdate.get().getError() == null) {
            resultOperationToChat(update, bot, true);
            receiveReqNum(update, bot, ticketOperationUpdate.get().getTicketNumber());
        } else {
            if (ticketOperationUpdate.get().getError() != null &&
                    ticketOperationUpdate.get().getError().getErrorCode().equals(ErrorState.NOT_AUTHORIZED.getCode())) {
                missingRegistration(update.getMessage(), bot);
            } else {
                sendErrorMsg(bot, update, update.getMessage().getReplyToMessage().getText(), ticketOperationUpdate.get()
                        .getError());
            }
        }
    }
}
