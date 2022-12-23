package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.state.Command;

@Component
public class AboutCommandHandler implements CommandHandler {

    private final VirtaBot bot;

    public AboutCommandHandler(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleCommand(Message message, String text, TelegramBotRegistration registration) throws TelegramApiException {
        bot.execute(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .parseMode(ParseMode.HTML)
//                .disableWebPagePreview(true)
                .text("""
                        <pre>Контактная информация:</pre>
                        Адрес: 109316, г.Москва, Волгоградский проспект, д.46Б, корп.1
                        Телефон: +7 (495) 775–81–30
                        Сайт: https://nis-pro.ru
                        Почта: sales@nis-team.ru
                        Группа в VK: https://vk.com/nis_team
                        """)
                .build());
        bot.execute(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .parseMode(ParseMode.HTML)
                .text("""
                        <i>Этот бот создан для взаимодействия с информационной системой НИС-Про – программа для бухгалтерского бизнеса</i>\040
                        """)
                .build());
    }

    @Override
    public Command getCommand() {
        return Command.ABOUT;
    }
}
