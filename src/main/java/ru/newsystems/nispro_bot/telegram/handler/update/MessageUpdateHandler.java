package ru.newsystems.nispro_bot.telegram.handler.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.integration.parser.CommandParser;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.dto.ParseDTO;
import ru.newsystems.nispro_bot.base.model.state.UpdateHandlerStage;
import ru.newsystems.nispro_bot.telegram.handler.update.messageVersion.Version;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.List;
import java.util.Optional;

import static ru.newsystems.nispro_bot.telegram.utils.Action.getMessage;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.missingRegistration;

@Component
public class MessageUpdateHandler implements UpdateHandler {

    private final VirtaBot bot;
    private final TelegramBotRegistrationService service;
    private final CommandParser commandParser;

    @Autowired
    private List<Version> messageHandlers;

    public MessageUpdateHandler(VirtaBot bot, TelegramBotRegistrationService service, CommandParser commandParser) {
        this.bot = bot;
        this.service = service;
        this.commandParser = commandParser;
    }

    @Override
    public boolean handleUpdate(Update update) throws TelegramApiException {

        Message message = getMessage(update);
        if (message == null) return false;
        String text = message.getText();
        Optional<ParseDTO> command = commandParser.parseCommand(text);
        if (command.isEmpty()) {
            TelegramBotRegistration registration = service.getByTelegramId(String.valueOf(update.getMessage().getChatId()));
            if (registration.getCompany() == null) {
                if (update.getMessage().getChatId() > 0) {
                    missingRegistration(update.getMessage(), bot);
                }
                return false;
            }

            for (Version messageHandler : messageHandlers) {
                try {
                    if (messageHandler.handle(update, registration.isLightVersion())) {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.MESSAGE;
    }

}
