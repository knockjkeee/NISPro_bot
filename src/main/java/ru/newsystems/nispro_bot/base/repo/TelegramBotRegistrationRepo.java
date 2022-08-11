package ru.newsystems.nispro_bot.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelegramBotRegistrationRepo extends JpaRepository<TelegramBotRegistration, Long> {

    List<TelegramBotRegistration> findByCompany(String company);

    Optional<TelegramBotRegistration> findByIdTelegram(String IdTelegram);

    Optional<TelegramBotRegistration> findByAgentIdTelegram(String agentIdTelegram);

    List<TelegramBotRegistration> findByCompanyContainingIgnoreCase(String company);

}
