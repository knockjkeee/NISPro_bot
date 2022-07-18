package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.state.Command;

@Component
public class AboutCommandHandler implements CommandHandler {

    private final VirtaBot bot;

    public AboutCommandHandler(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleCommand(Message message, String text) throws TelegramApiException {
        bot.execute(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .parseMode(ParseMode.HTML)
                .text("""
                        <i>Этот бот демонстрирует большинство возможностей
                        взаимодействия с информационной системой</i>\040
                        <pre>НИС-про|1.0</pre>
                       
                        <pre>Контактная информация:</pre>
                        Адрес: г. Москва, ул ... дом ...
                        Телефон: +7(499)123-45-67
                        Почта: info@nic-pro.ru
                        Группа в VK: https://wwww.vk.com
                        """)
                .build());
    }

    @Override
    public Command getCommand() {
        return Command.ABOUT;
    }
}
