package ru.newsystems.nispro_bot.telegram.handler.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.integration.parser.CommandParser;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.state.UpdateHandlerStage;
import ru.newsystems.nispro_bot.base.repo.local.MessageLocalRepo;
import ru.newsystems.nispro_bot.telegram.handler.message.MessageHandler;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.List;

import static ru.newsystems.nispro_bot.telegram.utils.Notification.missingRegistration;

//@Component
public class MessageUpdateLightHandler implements UpdateHandler {


    private final CommandParser commandParser;
    private final RestNISService restNISService;
    private final MessageLocalRepo localRepo;
    private final VirtaBot bot;
    private final TelegramBotRegistrationService service;

    @Autowired
    private List<MessageHandler> messageHandlers;

    public MessageUpdateLightHandler(CommandParser commandParser, RestNISService restNISService, MessageLocalRepo localRepo, VirtaBot bot, TelegramBotRegistrationService service) {
        this.commandParser = commandParser;
        this.localRepo = localRepo;
        this.restNISService = restNISService;
        this.bot = bot;
        this.service = service;
    }

    @Override
    public boolean handleUpdate(Update update) throws TelegramApiException {
        TelegramBotRegistration registration = registration(update.getMessage().getChatId());
        if (registration.getCompany() == null) {
            missingRegistration(update.getMessage(), bot);
            return false;
        }
        if (registration.isLightVersion()) {
        bot.execute(SendMessage
                .builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text(update.getMessage().getText())
                .parseMode(ParseMode.HTML)
                .build());
        return true;
        }else {
            missingRegistration(update.getMessage(), bot);
            return false;
        }
    }


    public TelegramBotRegistration registration(Long id) {
        return service.getByTelegramId(String.valueOf(id));
    }


    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.MESSAGE;
    }

}
