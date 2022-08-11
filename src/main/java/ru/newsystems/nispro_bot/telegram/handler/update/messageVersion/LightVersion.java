package ru.newsystems.nispro_bot.telegram.handler.update.messageVersion;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;

@Component
public class LightVersion implements Version {

    private final VirtaBot bot;

    public LightVersion(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public boolean handle(Update update, TelegramBotRegistration registration) throws TelegramApiException {
        if (!registration.isLightVersion()) return false;

        if (update.getMessage().getChatId() < 0) {
//            bot.execute(SendMessage.builder()
//                    .chatId(String.valueOf(update.getMessage().getChatId()))
//                    .text(update.getMessage().getText())
//                    .parseMode(ParseMode.HTML)
//                    .build());
        }
        return true;
    }
}
