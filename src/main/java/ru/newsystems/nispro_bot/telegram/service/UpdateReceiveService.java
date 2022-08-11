package ru.newsystems.nispro_bot.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.newsystems.nispro_bot.base.integration.Subscriber;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.telegram.handler.update.UpdateHandler;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateReceiveService implements Subscriber {

    private final VirtaBot bot;
    private List<UpdateHandler> updateHandlers;
    private final TelegramBotRegistrationService service;

    public UpdateReceiveService(VirtaBot bot, List<UpdateHandler> updateHandlers, TelegramBotRegistrationService service) {
        this.service = service;
        bot.subscribe(this);
        this.bot = bot;
        this.updateHandlers = updateHandlers;
    }

    @Override
    public void handleEvent(Update update) {
        TelegramBotRegistration registration = service.getByTelegramId(String.valueOf(update.getMessage().getChatId()));

        for (UpdateHandler updateHandler : updateHandlers) {
            try {
                if (updateHandler.handleUpdate(update, registration)) {
                    return;
                }
            } catch (Exception e) {
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

