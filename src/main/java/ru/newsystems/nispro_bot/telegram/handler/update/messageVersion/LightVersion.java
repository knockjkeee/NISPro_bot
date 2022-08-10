package ru.newsystems.nispro_bot.telegram.handler.update.messageVersion;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;

@Component
public class LightVersion implements Version {

    private final VirtaBot bot;

    public LightVersion(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public boolean handle(Update update, boolean isLightVersion) throws TelegramApiException {
        if (!isLightVersion) return false;
        bot.execute(SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text(update.getMessage().getText())
                .parseMode(ParseMode.HTML)
                .build());
        return true;
    }
}
