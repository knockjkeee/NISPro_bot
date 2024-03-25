package ru.newsystems.nispro_bot.telegram.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Error;

import static ru.newsystems.nispro_bot.telegram.utils.Messages.prepareHelpMsg;

@Component
public class Notification {

    @Value("${info.mail2}")
    private String mail;

    private static String MAIL_STATIC;

    @Value("${info.mail2}")
    public void setNameStatic(String mail){
        Notification.MAIL_STATIC = mail;
    }

    public static void resultOperationToChat(Update update, VirtaBot bot, boolean isSuccess) throws TelegramApiException {
        String text = isSuccess ? "<b>✅ Выполнено</b>" : "<b>⛔️ Ошибка в запросе</b>";
        if (update.hasCallbackQuery()) {
            bot.execute(SendMessage.builder()
                    .text(text)
                    .replyToMessageId(update.getCallbackQuery().getMessage().getMessageId())
                    .chatId(update.getCallbackQuery().getMessage().getChatId().toString())
                    .parseMode(ParseMode.HTML)
                    .build());
        } else {
            bot.execute(SendMessage.builder()
                    .text(text)
                    .replyToMessageId(update.getMessage().getMessageId())
                    .chatId(update.getMessage().getChatId().toString())
                    .parseMode(ParseMode.HTML)
                    .build());
        }
    }

    public static void resultOperationToChat(Message message, VirtaBot bot, boolean isSuccess) throws TelegramApiException {
        String text = isSuccess ? "<b>✅ Выполнено</b>" : "<b>❎ Открытых заявок нет</b>";
        bot.execute(SendMessage.builder()
                .text(text)
                .replyToMessageId(message.getMessageId())
                .chatId(message.getChatId().toString())
                .parseMode(ParseMode.HTML)
                .build());
    }

    public static void receiveReqNum(Update update, VirtaBot bot, Long reqNum) throws TelegramApiException {
        bot.execute(SendMessage.builder()
                .text("<b>Номер созданной заявки: " + reqNum + "</b>")
                .replyToMessageId(update.getMessage().getMessageId())
                .chatId(update.getMessage().getChatId().toString())
                .parseMode(ParseMode.HTML)
                .build());
    }

    public static void sendErrorMsg(VirtaBot bot, Update update, String text, Error error) throws TelegramApiException {
        String resultText =
                "❗️❗❗ \n<b>ErrorCode:</b>" + error.getErrorCode() + "" + "\n<b>ErrorMessage%</b>" +
                        error.getErrorMessage() + "" + "\nby text: " + text;
        if (update.hasCallbackQuery()) {
            bot.execute(SendMessage.builder()
                    .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                    .text(resultText)
                    .parseMode(ParseMode.HTML)
                    .replyToMessageId(update.getCallbackQuery().getMessage().getMessageId())
                    .build());
        } else {
            bot.execute(SendMessage.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .text(resultText)
                    .parseMode(ParseMode.HTML)
                    .replyToMessageId(update.getMessage().getMessageId())
                    .build());
        }
    }

    public static void queryIsMissing(Update update, VirtaBot bot) throws TelegramApiException {
        bot.execute(EditMessageText.builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .text("<b>Запрос закрыт по таймауту, для возобновления работы воспользуйтесь командой /my_ticket</b>")
                .parseMode(ParseMode.HTML)
                .build());
    }

    public static void sendExceptionMsg(Update update, String text, String service, VirtaBot bot) throws TelegramApiException {
        String resultText =
                "❗❗❗️ \n<b>Ошибка в запросе</b>" + "\nВ поиск передано не верное значение: [" + service + "] <b>" +
                        text + "</b>\nПовторите запрос с корректным id\n\n" +  prepareHelpMsg();
        bot.execute(SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text(resultText)
                .parseMode("html")
                .replyToMessageId(update.getMessage().getMessageId())
                .build());
    }

    public static void missingRegistration(Message message, VirtaBot bot) throws TelegramApiException {
        String text = "<b>⛔️ Регистрация отсутствует, запрос на регистрацию направить на почту "+ MAIL_STATIC +".</b>";
        bot.execute(SendMessage.builder()
                .text(text)
                .chatId(message.getChatId().toString())
                .parseMode(ParseMode.HTML)
                .build());
    }
}
