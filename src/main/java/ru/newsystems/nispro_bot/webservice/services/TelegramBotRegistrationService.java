package ru.newsystems.nispro_bot.webservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.repo.TelegramBotRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramBotRegistrationService {

    private final TelegramBotRepo repo;

    public TelegramBotRegistration getByTelegramId(String id) {
        Optional<TelegramBotRegistration> byIdTelegram = repo.findByIdTelegram(id);

        return byIdTelegram.orElseGet(TelegramBotRegistration::new);
    }

    public List<TelegramBotRegistration> addAll() {
        return repo.findAll();
    }

    public void save(TelegramBotRegistration entity) {
        repo.save(entity);
    }

    public void remove(TelegramBotRegistration entity) {
        repo.delete(entity);
    }

    public List<TelegramBotRegistration> findFilter(String name) {
        if (name == null || name.isEmpty()) {
            return repo.findAll();
        } else {
            return repo.findByCompanyContainingIgnoreCase(name);
        }
    }

    public void update(TelegramBotRegistration entity) {
        Optional<TelegramBotRegistration> opt = repo.findById(entity.getId());
        if (opt.isPresent()) {
            TelegramBotRegistration old = opt.get();
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



