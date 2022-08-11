package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.state.Command;
import ru.newsystems.nispro_bot.base.utils.StringUtil;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class MyIDCommandHandler implements CommandHandler {

    private final VirtaBot bot;

    public MyIDCommandHandler(VirtaBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleCommand(Message message, String text, TelegramBotRegistration registration) throws TelegramApiException {
        String txt, chatId;
        if (registration.isLightVersion() && !StringUtil.isBlank(registration.getAgentIdTelegram())) {
            chatId = registration.getAgentIdTelegram();
            txt = "<pre>Id группы компании " + registration.getCompany() + ": " + message.getChatId() + "</pre>";
            ArrayList<ChatMember> execute =
                    bot.execute(GetChatAdministrators.builder().chatId(String.valueOf(message.getChatId())).build());
            String membersGroup = "<pre>Участники группы от компании: " + registration.getCompany() + "</pre>\n" +
                    execute.stream()
                            .map(e -> e.getUser().getFirstName() + " " + e.getUser().getLastName() + ", isBot: " +
                                    e.getUser().getIsBot() + ", id: " + e.getUser().getId())
                            .collect(Collectors.joining("\n"));
            bot.execute(SendMessage.builder().chatId(chatId).parseMode("html").text(membersGroup).build());
        } else {
            chatId = message.getChatId().toString();
            txt = "<pre>Ваш id : " + message.getChatId() + "</pre>";
        }
        bot.execute(SendMessage.builder().chatId(chatId).parseMode("html").text(txt).build());
    }

    @Override
    public Command getCommand() {
        return Command.MY_ID;
    }
}
