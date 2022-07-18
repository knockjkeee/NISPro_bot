package ru.newsystems.nispro_bot.webservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.newsystems.nispro_bot.base.model.db.TelegramBot;
import ru.newsystems.nispro_bot.base.repo.TelegramBotRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramRegistrationBotService {

    private final TelegramBotRepo repo;


    public List<TelegramBot> addAll() {
        return repo.findAll();
    }

    public void save(TelegramBot entity) {
        repo.save(entity);
    }

    public void remove(TelegramBot entity) {
        repo.delete(entity);
    }

    public List<TelegramBot> findFilter(String name) {
        if (name == null || name.isEmpty()) {
            return repo.findAll();
        } else {
            return repo.findByCompanyContainingIgnoreCase(name);
        }
    }

    public void update(TelegramBot entity) {
        Optional<TelegramBot> opt = repo.findById(entity.getId());
        if (opt.isPresent()) {
            TelegramBot old = opt.get();
            old.setCompany(entity.getCompany());
            old.setLogin(entity.getLogin());
            old.setPassword(entity.getPassword());
            old.setUrl(entity.getUrl());
            old.setQueueId(entity.getQueueId());
            repo.save(old);
        } else {
            repo.save(entity);
        }
    }
}



