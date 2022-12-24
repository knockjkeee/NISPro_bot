package ru.newsystems.nispro_bot.webservice.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.db.TelegramReceiveNotificationNewArticle;
import ru.newsystems.nispro_bot.base.model.domain.NotificationNewArticle;
import ru.newsystems.nispro_bot.base.model.domain.handleServices.Article;
import ru.newsystems.nispro_bot.base.model.domain.handleServices.Ticket;
import ru.newsystems.nispro_bot.base.repo.TelegramBotRegistrationRepo;
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
    private final TelegramBotRegistrationRepo registrationRepo;

    public TelegramBotNotificationService(TelegramNotificationRepo repo, ScheduledExecutorService executor, VirtaBot bot, RestNISService rest, TelegramBotRegistrationRepo registrationRepo) {
        this.repo = repo;
        this.executor = executor;
        this.bot = bot;
        this.rest = rest;
        this.registrationRepo = registrationRepo;
    }

    @SneakyThrows
    public void saveEntity(Ticket ticket, Article article, String named){
        if (validateDuplicate(article)) return;
        TelegramReceiveNotificationNewArticle notificationNewArticle = new TelegramReceiveNotificationNewArticle();
        prepareDefaultFieldNotificationNewArticle(ticket, article, notificationNewArticle);
        boolean isMe;
        try {
            isMe = article.getFromRealname().equals( ticket.getOwnerData().getUserFullname());
        } catch (Exception e) {
            isMe = true;
        }
        notificationNewArticle.setLoginCountRegistration((long) (isMe ? 1 : 0));
        repo.save(notificationNewArticle);
    }

    @SneakyThrows
    public void saveEntity(Ticket ticket, Article article){
        if (validateDuplicate(article)) return;
        //if (ticket.getOwner().equals(ticket.getResponsible())) return;

        List<TelegramBotRegistration> byLogin = registrationRepo.findByLoginAndQueueName(ticket.getResponsible(), ticket.getQueue());
        if (byLogin.size() == 0) return;

        TelegramReceiveNotificationNewArticle notificationNewArticle = new TelegramReceiveNotificationNewArticle();
        prepareDefaultFieldNotificationNewArticle(ticket, article, notificationNewArticle);
        notificationNewArticle.setIdTelegram(byLogin.get(0).getIdTelegram());
        notificationNewArticle.setLoginCountRegistration(0L);
        notificationNewArticle.setResponsible(true);
        repo.save(notificationNewArticle);
    }

    private boolean validateDuplicate(Article article) {
        List<TelegramReceiveNotificationNewArticle> all = repo.findAll();
        List<TelegramReceiveNotificationNewArticle> collect = all.stream()
                .filter(e -> Objects.equals(e.getArticleId(), article.getArticleID()))
                .collect(Collectors.toList());
        if (collect.size() > 0) return true;
        return false;
    }

    private void prepareDefaultFieldNotificationNewArticle(Ticket ticket, Article article, TelegramReceiveNotificationNewArticle notificationNewArticle) {
        notificationNewArticle.setArticleId(article.getArticleID());
        notificationNewArticle.setBody(article.getBody());
        notificationNewArticle.setCreateBy(article.getFromRealname());
        notificationNewArticle.setIdTelegram(ticket.getDynamicFieldTelegram());
        notificationNewArticle.setIsVisibleForCustomer(article.getIsVisibleForCustomer());
        notificationNewArticle.setQueueId(String.valueOf(ticket.getQueueID()));
        notificationNewArticle.setSubject(article.getSubject());
        notificationNewArticle.setTicketNumber(String.valueOf(ticket.getTicketNumber()));
    }

    @PostConstruct
    private void init() {
        NotificationNewArticle notificationNewArticle =
                NotificationNewArticle.builder().bot(bot).repo(repo).rest(rest).build();
        executor.scheduleAtFixedRate(notificationNewArticle, 30, 60, TimeUnit.SECONDS);
    }

}
