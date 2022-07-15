package ru.newsystems.nispro_bot.base.integration.parser;

import ru.newsystems.nispro_bot.base.model.dto.ParseDTO;

import java.util.Optional;

public interface Parser {
    Optional<ParseDTO> parseCommand(String msg);
}
