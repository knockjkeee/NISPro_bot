package ru.newsystems.nispro_bot.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.newsystems.nispro_bot.base.model.db.TelegramReceiveNotificationNewArticle;

@Repository
public interface TelegramNotificationRepo extends JpaRepository<TelegramReceiveNotificationNewArticle, Long> {

}
