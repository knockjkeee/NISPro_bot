package ru.newsystems.nispro_bot.webservice.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.newsystems.nispro_bot.base.integration.Subscriber;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.webservice.handler.update.UpdateHandler;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateReceiveService implements Subscriber {

    private final VirtaBot bot;
    private List<UpdateHandler> updateHandlers;

    public UpdateReceiveService(VirtaBot bot, List<UpdateHandler> updateHandlers) {
        bot.subscribe(this);
        this.bot = bot;
        this.updateHandlers = updateHandlers;
    }

    @Override
    public void handleEvent(Update update) {
        for (UpdateHandler updateHandler : updateHandlers) {
            try {
                if (updateHandler.handleUpdate(update)) {
                    //TODO log good work
                    return;
                }
                //TODO log bad work, search entity
            } catch (Exception e) {
                //TODO log excpt work
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void init() {
        updateHandlers = updateHandlers.stream()
                .sorted(Comparator.comparingInt(u -> u.getStage().getOrder()))
                .collect(Collectors.toList());
    }

}

