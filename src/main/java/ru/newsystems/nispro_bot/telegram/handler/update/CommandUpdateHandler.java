package ru.newsystems.nispro_bot.telegram.handler.update;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.parser.CommandParser;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.dto.ParseDTO;
import ru.newsystems.nispro_bot.base.model.state.Command;
import ru.newsystems.nispro_bot.base.model.state.UpdateHandlerStage;
import ru.newsystems.nispro_bot.telegram.handler.command.CommandHandler;
import ru.newsystems.nispro_bot.telegram.handler.command.CommandHandlerFactory;

import java.util.Optional;

import static ru.newsystems.nispro_bot.telegram.utils.Action.getMessage;


@Component
public class CommandUpdateHandler implements UpdateHandler {

    private final CommandParser commandParser;
    private final CommandHandlerFactory commandHandlerFactory;

    public CommandUpdateHandler(CommandParser commandParser, CommandHandlerFactory commandHandlerFactory) {
        this.commandParser = commandParser;
        this.commandHandlerFactory = commandHandlerFactory;
    }

    @Override
    public boolean handleUpdate(Update update, TelegramBotRegistration registration) throws TelegramApiException {
        Message message = getMessage(update);
        if (message == null) return false;
        String text = message.hasPhoto() ? message.getCaption() : message.getText();
        Optional<ParseDTO> command = commandParser.parseCommand(text);
        if (command.isEmpty()) {
            return message.hasPhoto() || message.hasDocument();
        }

        boolean isMyId = !command.get().getCommand().getName().equals(Command.MY_ID.getName());
        if ((update.getMessage().getChatId() < 0 && isMyId) || (registration.isLightVersion() && isMyId)) {
            return false;
        }
        handleCommand(update, command.get().getCommand(), command.get().getText(), registration);
        return true;
    }

    private void handleCommand(Update update, Command command, String text, TelegramBotRegistration registration) throws TelegramApiException {
        CommandHandler commandHandler = commandHandlerFactory.getHandler(command);
        commandHandler.handleCommand(update.getMessage(), text, registration);
    }

    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.COMMAND;
    }
}
