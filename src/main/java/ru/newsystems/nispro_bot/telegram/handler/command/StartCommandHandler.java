package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.state.Command;

@Component
public class StartCommandHandler implements CommandHandler {

    private final VirtaBot bot;

    public StartCommandHandler(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleCommand(Message message, String text) throws TelegramApiException {
        bot.execute(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .parseMode(ParseMode.HTML)
                .text("""
                        <pre>Тут будет первичная проверка на регистрацию</pre>
                        """)
                .build());
    }

    @Override
    public Command getCommand() {
        return Command.START;
    }
}
