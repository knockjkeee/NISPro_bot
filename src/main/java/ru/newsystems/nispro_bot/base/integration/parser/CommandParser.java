package ru.newsystems.nispro_bot.base.integration.parser;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;
import ru.newsystems.nispro_bot.base.model.dto.ParseDTO;
import ru.newsystems.nispro_bot.base.model.state.Command;
import ru.newsystems.nispro_bot.base.utils.StringUtil;

import java.util.Optional;

@Component
public class CommandParser implements Parser {

    @Override
    public Optional<ParseDTO> parseCommand(String msg) {
        if (StringUtil.isBlank(msg)) {
            return Optional.empty();
        }
        String trimText = StringUtil.trim(msg);
        ImmutablePair<String, String> commandAndText = getDelimitedCommandFromText(trimText);
        if (isCommand(commandAndText.getKey())) {
            Optional<Command> command = Command.parseCommand(commandAndText.getKey());
            return command.map(com -> new ParseDTO(com, commandAndText.getValue()));
        }
        return Optional.empty();
    }


    private ImmutablePair<String, String> getDelimitedCommandFromText(String trimText) {
        ImmutablePair<String, String> commandText;
        if (trimText.contains(" ")) {
            int indexOfSpace = trimText.indexOf(" ");
            commandText = new ImmutablePair<>(trimText.substring(0, indexOfSpace), trimText.substring(indexOfSpace
                    + 1));
        } else commandText = new ImmutablePair<>(trimText, "");
        return commandText;
    }

    private boolean isCommand(String text) {
        String PREFIX_FOR_COMMAND = "/";
        return text.startsWith(PREFIX_FOR_COMMAND);
    }
}
