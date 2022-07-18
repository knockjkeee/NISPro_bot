package ru.newsystems.nispro_bot.telegram.handler.update;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.model.state.UpdateHandlerStage;

public interface UpdateHandler {

    boolean handleUpdate(Update update) throws TelegramApiException;

    UpdateHandlerStage getStage();
}
