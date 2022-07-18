package ru.newsystems.nispro_bot.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelegramBotRepo extends JpaRepository<TelegramBotRegistration, Long> {

    Optional<TelegramBotRegistration> findByCompany(String name);

    Optional<TelegramBotRegistration> findByIdTelegram(String id);

    List<TelegramBotRegistration> findByCompanyContainingIgnoreCase(String company);

}
