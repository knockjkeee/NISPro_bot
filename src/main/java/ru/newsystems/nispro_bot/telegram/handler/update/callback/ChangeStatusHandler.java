package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.dto.callback.ChangeStatusDTO;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Component
@Log4j2
public class ChangeStatusHandler extends CallbackUpdateHandler<ChangeStatusDTO>{

    @Override
    protected Class<ChangeStatusDTO> getDtoType() {
        return ChangeStatusDTO.class;
    }

    @Override
    protected SerializableInlineType getSerializableType() {
        return SerializableInlineType.CHANGE_STATUS;
    }

    @Override
    protected void handleCallback(Update update, ChangeStatusDTO dto, TelegramBotRegistration registration) throws TelegramApiException {
        log.debug("asd");
    }
}
