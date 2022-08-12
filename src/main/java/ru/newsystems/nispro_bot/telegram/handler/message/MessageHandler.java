package ru.newsystems.nispro_bot.telegram.handler.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface MessageHandler {
    boolean handleUpdate(Update update, boolean isRedirect) throws TelegramApiException;
}
