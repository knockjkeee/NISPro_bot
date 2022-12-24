package ru.newsystems.nispro_bot.base.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "telegram_receive_notification_new_article")
public class TelegramReceiveNotificationNewArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tn")
    private String ticketNumber;

    @Column(name = "id_telegram")
    private String idTelegram;

    @Column(name = "queue_id")
    private String queueId;

    @Column(name = "is_visible_for_customer")
    private Long isVisibleForCustomer;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "login_count_registration")
    private Long loginCountRegistration;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body", length = 99999999)
    private String body;

    @Column(name = "responsible")
    private boolean responsible;
}