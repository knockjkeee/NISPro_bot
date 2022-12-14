package ru.newsystems.nispro_bot.webservice.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramReceiveNotificationNewArticle;
import ru.newsystems.nispro_bot.base.model.domain.NotificationNewArticle;
import ru.newsystems.nispro_bot.base.model.domain.handleServices.Article;
import ru.newsystems.nispro_bot.base.model.domain.handleServices.Ticket;
import ru.newsystems.nispro_bot.base.repo.TelegramNotificationRepo;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TelegramBotNotificationService {

    private final TelegramNotificationRepo repo;
    private final ScheduledExecutorService executor;
    private final VirtaBot bot;
    private final RestNISService rest;

    public TelegramBotNotificationService(TelegramNotificationRepo repo, ScheduledExecutorService executor, VirtaBot bot, RestNISService rest) {
        this.repo = repo;
        this.executor = executor;
        this.bot = bot;
        this.rest = rest;
    }


    @SneakyThrows
    public void saveEntity(Ticket ticket, Article article, String named){

        List<TelegramReceiveNotificationNewArticle> all = repo.findAll();

        List<TelegramReceiveNotificationNewArticle> collect = all.stream()
                .filter(e -> Objects.equals(e.getArticleId(), article.getArticleID()))
                .collect(Collectors.toList());
        if (collect.size() > 0) return;

        TelegramReceiveNotificationNewArticle notificationNewArticle = new TelegramReceiveNotificationNewArticle();
        notificationNewArticle.setArticleId(article.getArticleID());
        notificationNewArticle.setBody(article.getBody());
        notificationNewArticle.setCreateBy(article.getFromRealname());
        notificationNewArticle.setIdTelegram(ticket.getDynamicFieldTelegram());
        notificationNewArticle.setIsVisibleForCustomer(article.getIsVisibleForCustomer());
        boolean isMe =article.getFromRealname().equals( ticket.getOwnerData().getUserFullname());
        notificationNewArticle.setLoginCountRegistration((long) (isMe ? 1 : 0));
        notificationNewArticle.setQueueId(String.valueOf(ticket.getQueueID()));
        notificationNewArticle.setSubject(article.getSubject());
        notificationNewArticle.setTicketNumber(String.valueOf(ticket.getTicketNumber()));
        repo.save(notificationNewArticle);
    }

    @PostConstruct
    private void init() {
        NotificationNewArticle notificationNewArticle =
                NotificationNewArticle.builder().bot(bot).repo(repo).rest(rest).build();
        executor.scheduleAtFixedRate(notificationNewArticle, 1, 2, TimeUnit.MINUTES);
    }

}
