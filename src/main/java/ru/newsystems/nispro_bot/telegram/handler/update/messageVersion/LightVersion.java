package ru.newsystems.nispro_bot.telegram.handler.update.messageVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.telegram.handler.message.MessageHandler;

import java.util.List;

@Component
public class LightVersion implements Version {

    private final VirtaBot bot;

    @Autowired
    private List<MessageHandler> messageHandlers;

    public LightVersion(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public boolean handle(Update update, TelegramBotRegistration registration) throws TelegramApiException {
        if (!registration.isLightVersion()) return false;
        boolean isRedirect = update.getMessage().getForwardFrom() != null;
        if (update.getMessage().getChatId() > 0) {
            for (MessageHandler messageHandler : messageHandlers) {
            try {
                if (messageHandler.handleUpdate(update, isRedirect)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
        }
        return true;
    }
}
