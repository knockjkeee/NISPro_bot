package ru.newsystems.nispro_bot.base.model.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.newsystems.nispro_bot.base.model.domain.Error;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;

import java.util.List;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketGetDTO {
    @JsonProperty("Error")
    private Error error;
    @JsonProperty("Ticket")
    private List<TicketJ> tickets;

    private String currentTickerNumber;
    private String currentArticle;
}
