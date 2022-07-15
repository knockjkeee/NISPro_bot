package ru.newsystems.nispro_bot.base.repo.local;

import org.springframework.stereotype.Component;
import ru.newsystems.nispro_bot.base.model.dto.MessageGetDTO;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class MessageLocalRepo {

    ConcurrentMap<Long, MessageGetDTO> repo = new ConcurrentHashMap<>();

    public void add(Long id, MessageGetDTO data) {
        repo.put(id, data);
    }

    public void remove(Long id) {
        repo.remove(id);
    }

    public void update(Long id, MessageGetDTO data) {
        MessageGetDTO old = repo.get(id);
        if (old == null) {
            repo.put(id, data);
        } else {
            repo.replace(id, old, data);
        }
    }

    public MessageGetDTO get(Long id) {
        return repo.get(id);
    }
}
