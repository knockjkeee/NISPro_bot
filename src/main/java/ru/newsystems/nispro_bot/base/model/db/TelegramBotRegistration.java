package ru.newsystems.nispro_bot.base.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "telegram_bot_registration")
public class TelegramBotRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
//    @NotEmpty
    private Long id;

    @Column(name = "company", nullable = false, length = 100)
    @NotEmpty
    private String company;

    @Column(name = "id_telegram", nullable = false, length = 100)
    @NotEmpty
    private String idTelegram;

    @Column(name = "url", nullable = false, length = 100)
    @NotEmpty
    private String url;

    @Column(name = "login", nullable = false, length = 100)
    @NotEmpty
    private String login;

    @Column(name = "password", nullable = false, length = 100)
    @NotEmpty
    private String password;

    @Column(name = "queue_id", nullable = false, length = 100)
    @NotEmpty
    private String queueId;

    @Column(name = "customer_User", nullable = false, length = 100)
    @NotEmpty
    private String customerUser;

}