package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.state.Command;

import static ru.newsystems.nispro_bot.telegram.utils.Messages.prepareHelpMsg;

@Component
public class HelpCommandHandler implements CommandHandler {

    private final VirtaBot bot;

    public HelpCommandHandler(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleCommand(Message message, String text, TelegramBotRegistration registration) throws TelegramApiException {
        bot.execute(
                SendMessage.builder()
                        .text(prepareHelpMsg())
                        .parseMode(ParseMode.HTML)
                        .chatId(message.getChatId().toString())
                        .build());
    }

    @Override
    public Command getCommand() {
        return Command.HELP;
    }
}
