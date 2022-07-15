package ru.newsystems.nispro_bot.base.integration;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Customer {
    void subscribe(Subscriber o);
    void unsubscribe(Subscriber o);
    void notificationSubscribers(Update update);
}
