package ru.newsystems.nispro_bot.webservice.Telegram;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketUpdateCreateDTO;
import ru.newsystems.nispro_bot.webservice.service.RestService;

import java.util.Optional;

import static ru.newsystems.nispro_bot.webservice.Telegram.Notification.*;


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

    public static void sendNewComment(Update update, RequestDataDTO req, RestService restService, VirtaBot bot) throws TelegramApiException {
        Optional<TicketUpdateCreateDTO> ticketOperationUpdate = restService.getTicketOperationUpdate(req);
        if (ticketOperationUpdate.isPresent() && ticketOperationUpdate.get().getError() == null) {
            resultOperationToChat(update, bot, true);
        } else {
            sendErrorMsg(bot, update, update.getMessage().getReplyToMessage().getText(), ticketOperationUpdate
                    .get()
                    .getError());
        }
    }

    public static void sendCreateTicket(Update update, RequestDataDTO req, RestService restService, VirtaBot bot) throws TelegramApiException {
        Optional<TicketUpdateCreateDTO> ticketOperationUpdate = restService.getTicketOperationCreate(req);
        if (ticketOperationUpdate.isPresent() && ticketOperationUpdate.get().getError() == null) {
            resultOperationToChat(update, bot, true);
            receiveReqNum(update, bot, ticketOperationUpdate.get().getTicketNumber());
        } else {
            sendErrorMsg(bot, update, update.getMessage().getReplyToMessage().getText(), ticketOperationUpdate
                    .get()
                    .getError());
        }
    }
}
