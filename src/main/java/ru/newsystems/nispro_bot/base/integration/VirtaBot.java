package ru.newsystems.nispro_bot.base.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.newsystems.nispro_bot.base.model.state.Command;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VirtaBot extends TelegramLongPollingBot implements Customer {

    List<Subscriber> subscribers = new ArrayList<>();

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        notificationSubscribers(update);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void subscribe(Subscriber o) {
        subscribers.add(o);
    }

    @Override
    public void unsubscribe(Subscriber o) {
        subscribers.remove(o);
    }

    @Override
    public void notificationSubscribers(Update update) {
        subscribers.forEach(e -> e.handleEvent(update));
    }

    @PostConstruct
    private void setupCommands() {
        try {
            List<BotCommand> commands =
                    Arrays.stream(Command.values())
                            .map(c -> BotCommand.builder().command(c.getName()).description(c.getDesc()).build())
                            .collect(Collectors.toList());
            //TODO add my command
//            execute(SetMyCommands.builder().commands(commands).build());
//            commands.forEach(e -> {
//                System.out.println(e.getCommand() + " - " + e.getDescription());
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
