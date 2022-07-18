package ru.newsystems.nispro_bot.telegram.handler.command;

import org.springframework.stereotype.Component;
import ru.newsystems.nispro_bot.base.model.state.Command;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CommandHandlerFactory {
    private final List<CommandHandler> handlers;
    private Map<Command, CommandHandler> map;

    public CommandHandlerFactory(List<CommandHandler> handlers) {
        this.handlers = handlers;
    }

    @PostConstruct
    private void init() {
        map = new HashMap<>();
        handlers.forEach(h -> map.put(h.getCommand(), h));
    }

    public CommandHandler getHandler(Command command) {
        return Optional.ofNullable(map.get(command))
                .orElseThrow(() -> new IllegalStateException("Not supported command: " + command));
    }
}
