package ru.newsystems.nispro_bot.base.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.state.MessageState;

@Data
@RequiredArgsConstructor
public class MessageGetDTO {
    TicketJ ticket;
    MessageState state;
}
