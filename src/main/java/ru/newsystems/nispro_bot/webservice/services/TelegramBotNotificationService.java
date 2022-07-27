package ru.newsystems.nispro_bot.webservice.services;

import org.springframework.stereotype.Service;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.NotificationNewArticle;
import ru.newsystems.nispro_bot.base.repo.TelegramNotificationRepo;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TelegramBotNotificationService {

    private final TelegramNotificationRepo repo;
    private final ScheduledExecutorService executor;
    private final VirtaBot bot;

    public TelegramBotNotificationService(TelegramNotificationRepo repo, ScheduledExecutorService executor, VirtaBot bot) {
        this.repo = repo;
        this.executor = executor;
        this.bot = bot;
    }


    @PostConstruct
    private void init() {
        NotificationNewArticle notificationNewArticle = NotificationNewArticle.builder().bot(bot).repo(repo).build();
        executor.scheduleAtFixedRate(notificationNewArticle, 1, 2, TimeUnit.MINUTES);
    }

}
