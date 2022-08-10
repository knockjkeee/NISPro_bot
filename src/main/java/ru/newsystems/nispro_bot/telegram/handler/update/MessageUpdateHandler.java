package ru.newsystems.nispro_bot.telegram.handler.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.state.UpdateHandlerStage;
import ru.newsystems.nispro_bot.telegram.handler.update.messageVersion.Version;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.List;

import static ru.newsystems.nispro_bot.telegram.utils.Notification.missingRegistration;

@Component
public class MessageUpdateHandler implements UpdateHandler {

    private final VirtaBot bot;
    private final TelegramBotRegistrationService service;

    @Autowired
    private List<Version> messageHandlers;

    public MessageUpdateHandler(VirtaBot bot, TelegramBotRegistrationService service) {
        this.bot = bot;
        this.service = service;
    }

    @Override
    public boolean handleUpdate(Update update) throws TelegramApiException {
        TelegramBotRegistration registration = registration(update.getMessage().getChatId());
        if (registration.getCompany() == null) {
            missingRegistration(update.getMessage(), bot);
            return false;
        }
        for (Version messageHandler : messageHandlers) {
            try {
                if (messageHandler.handle(update, registration.isLightVersion())) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public TelegramBotRegistration registration(Long id) {
        return service.getByTelegramId(String.valueOf(id));
    }

    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.MESSAGE;
    }

}
