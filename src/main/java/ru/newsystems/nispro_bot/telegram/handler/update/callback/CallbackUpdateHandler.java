package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.model.dto.callback.SerializableInlineObject;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;
import ru.newsystems.nispro_bot.base.model.state.UpdateHandlerStage;
import ru.newsystems.nispro_bot.base.utils.StringUtil;
import ru.newsystems.nispro_bot.telegram.handler.update.UpdateHandler;

import java.util.Optional;

@Slf4j
public abstract class CallbackUpdateHandler<T extends SerializableInlineObject>
    implements UpdateHandler {

  protected abstract Class<T> getDtoType();

  protected abstract SerializableInlineType getSerializableType();

  protected abstract void handleCallback(Update update, T dto) throws TelegramApiException;

  @Override
  public boolean handleUpdate(Update update) throws TelegramApiException {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    if (callbackQuery == null || callbackQuery.getMessage() == null) {
      return false;
    }
    String data = callbackQuery.getData();
    Optional<T> dto = StringUtil.deserialize(data, getDtoType());
    if (!dto.isPresent() || dto.get().getIndex() != getSerializableType().getIndex()) {
      return false;
    }
    log.info("Found callback {}", getSerializableType());
    handleCallback(update, dto.get());
    return true;
  }

  @Override
  public UpdateHandlerStage getStage() {
    return UpdateHandlerStage.CALLBACK;
  }
}
