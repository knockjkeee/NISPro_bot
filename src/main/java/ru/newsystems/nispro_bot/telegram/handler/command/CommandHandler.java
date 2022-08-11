package ru.newsystems.nispro_bot.telegram.handler.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.state.Command;

public interface CommandHandler {
    void handleCommand(Message message, String text, TelegramBotRegistration registration) throws TelegramApiException;

    Command getCommand();
}
