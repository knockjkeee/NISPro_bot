package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.state.Command;
import ru.newsystems.nispro_bot.base.model.state.MessageState;

@Component
public class CreateTicketCommandHandler implements CommandHandler {

    private final VirtaBot bot;

    public CreateTicketCommandHandler(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleCommand(Message message, String text) throws TelegramApiException {
        bot.execute(SendMessage
                .builder()
                .text("<pre>" + MessageState.CREATETICKET.getName() + "</pre>")
                .chatId(String.valueOf(message.getChatId()))
                .parseMode(ParseMode.HTML)
                .replyMarkup(ForceReplyKeyboard.builder().forceReply(true).build())
                .build());
    }

    @Override
    public Command getCommand() {
        return Command.CREATE_TICKET;
    }
}
