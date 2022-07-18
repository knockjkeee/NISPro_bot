package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.state.Command;

@Component
public class MyIDCommandHandler implements CommandHandler {

    private final VirtaBot bot;

    public MyIDCommandHandler(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleCommand(Message message, String text) throws TelegramApiException {
        String txt = "<pre>Ваш id : " + message.getChatId() + "</pre>";

        bot.execute(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .parseMode("html")
                .text(txt)
                .build());
    }

    @Override
    public Command getCommand() {
        return Command.MY_ID;
    }
}
