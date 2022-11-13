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

    @Column(name = "company", nullable = false)
    @NotEmpty
    private String company;

    @Column(name = "id_telegram", nullable = false)
    @NotEmpty
    private String idTelegram;

    @Column(name = "agent_idtTelegram", nullable = false)
    private String agentIdTelegram;

    @Column(name = "chat_members", nullable = false)
    private String chatMembers;

    @Column(name = "url", nullable = false)
    @NotEmpty
    private String url;

    @Column(name = "login", nullable = false)
    @NotEmpty
    private String login;

    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "queue_name", nullable = false)
    @NotEmpty
    private String queueName;

    @Column(name = "customer_User", nullable = false)
    @NotEmpty
    private String customerUser;

    @Column(name = "light_version", nullable = false)
    private boolean lightVersion;

    @Column(name = "customer_login", nullable = false)
    private boolean customerLogin;

}