package ru.newsystems.nispro_bot.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.newsystems.nispro_bot.base.model.db.TelegramBot;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelegramBotRepo extends JpaRepository<TelegramBot, Long> {

    Optional<TelegramBot> findByCompany(String name);

//    @Query("select c from TelegramBot c " +
//            "where lower(c.company) like lower(concat('%', :searchTerm, '%')) " +
//            "or lower(c.login) like lower(concat('%', :searchTerm, '%'))")
//    List<TelegramBot> findByFilter(String name);
    List<TelegramBot> findByCompanyContainingIgnoreCase(String company);

}
