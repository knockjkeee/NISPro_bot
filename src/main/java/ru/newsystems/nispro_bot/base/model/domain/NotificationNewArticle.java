package ru.newsystems.nispro_bot.base.model.domain;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramReceiveNotificationNewArticle;
import ru.newsystems.nispro_bot.base.repo.TelegramNotificationRepo;

import java.util.List;

@Data
@Builder
public class NotificationNewArticle implements Runnable {
    TelegramNotificationRepo repo;
    private VirtaBot bot;

    @Override
    public void run() {
        List<TelegramReceiveNotificationNewArticle> newArticles = repo.findAll();
        if (newArticles.size() > 0) {
            newArticles.stream()
                    .filter(i -> i.getIsVisibleForCustomer() == 1 && i.getLoginCountRegistration() == 0)
                    .forEach(newArticle -> {
                        try {
                            int i = Integer.parseInt(newArticle.getIdTelegram());
                            sendNotification(newArticle);
                        } catch (NumberFormatException ignored) {
                        }
                    });
        }
        repo.deleteAll();
    }

    private void sendNotification(TelegramReceiveNotificationNewArticle newArticle) {
        try {
            bot.execute(SendMessage.builder()
                    .chatId(newArticle.getIdTelegram())
                    .text("<pre>✉️ Новое сообщение по заявке № " + newArticle.getTicketNumber() + "</pre>" +
                            "<pre>От " + newArticle.getCreateBy() + "</pre>" + "<b>Тема: </b><i>" +
                            newArticle.getSubject() + "</i>\n<b>Сообщение: </b><i>" + newArticle.getBody() + "</i>")
                    .parseMode(ParseMode.HTML)
                    .protectContent(true)
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
