package ru.newsystems.nispro_bot.webservice.task;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SendLocalRepo {

    ConcurrentMap<Long, SendDataDTO> repo = new ConcurrentHashMap<>();

    public void add(Long id, SendDataDTO data) {
        repo.put(id, data);
    }

    public void remove(Long id) {
        repo.remove(id);
    }

    public void update(Long id, SendDataDTO data) {
        SendDataDTO old = repo.get(id);
        if (old == null) {
            repo.put(id, data);
        } else {
            repo.replace(id, old, data);
        }
    }

    public SendDataDTO get(Long id) {
        return repo.get(id);
    }

}
