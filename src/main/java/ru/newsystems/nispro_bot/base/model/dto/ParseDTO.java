package ru.newsystems.nispro_bot.base.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.newsystems.nispro_bot.base.model.state.Command;

@Data
@RequiredArgsConstructor
public class ParseDTO {
    private final Command command;
    private final String text;
}
