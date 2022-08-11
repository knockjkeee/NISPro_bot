package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.domain.Error;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketSearchDTO;
import ru.newsystems.nispro_bot.base.model.state.Command;
import ru.newsystems.nispro_bot.base.model.state.ErrorState;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;

import static ru.newsystems.nispro_bot.telegram.utils.Notification.missingRegistration;

@Component
public class StartCommandHandler implements CommandHandler {

    private final VirtaBot bot;
    private final RestNISService restNISService;

    public StartCommandHandler(VirtaBot bot, RestNISService restNISService) {
        this.bot = bot;
        this.restNISService = restNISService;
    }

    @Override
    public void handleCommand(Message message, String text, TelegramBotRegistration registration) throws TelegramApiException {
        if (registration.getCompany() == null) {
            TicketSearchDTO temp = new TicketSearchDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            missingRegistration(message, bot);
        }
    }

    @Override
    public Command getCommand() {
        return Command.START;
    }
}
