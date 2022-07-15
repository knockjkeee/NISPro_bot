package ru.newsystems.nispro_bot.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.newsystems.nispro_bot.base.model.db.Ticket;

import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTn(String name);
}
