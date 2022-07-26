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

    @Column(name = "tn", nullable = false, length = 100)
    private String ticketNumber;

    @Column(name = "id_telegram", nullable = false, length = 100)
    private String idTelegram;

    @Column(name = "queue_id", nullable = false, length = 100)
    private String queueId;

    @Column(name = "is_visible_for_customer", nullable = false, length = 100)
    private Long isVisibleForCustomer;

}