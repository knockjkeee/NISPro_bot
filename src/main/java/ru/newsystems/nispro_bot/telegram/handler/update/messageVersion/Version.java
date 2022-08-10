package ru.newsystems.nispro_bot.telegram.handler.update.messageVersion;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Version {
    boolean handle(Update update, boolean isLightVersion) throws TelegramApiException;
}
