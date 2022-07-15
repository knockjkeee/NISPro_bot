package ru.newsystems.nispro_bot.base.integration;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Subscriber {
    void handleEvent(Update update);
}
