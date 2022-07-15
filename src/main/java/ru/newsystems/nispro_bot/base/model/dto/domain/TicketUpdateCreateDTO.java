package ru.newsystems.nispro_bot.base.model.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.newsystems.nispro_bot.base.model.domain.Error;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketUpdateCreateDTO {
    @JsonProperty("Error")
    private Error error;
    @JsonProperty("ArticleID")
    private String articleID;
    @JsonProperty("TicketID")
    private String ticketID;
    @JsonProperty("TicketNumber")
    private Long ticketNumber;
}
