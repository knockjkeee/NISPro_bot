package ru.newsystems.nispro_bot.telegram.handler.update.messageVersion;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;

public interface Version {
    boolean handle(Update update, TelegramBotRegistration registration) throws TelegramApiException;
}
