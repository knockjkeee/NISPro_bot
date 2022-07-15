package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Getter
@Setter
public class TicketViewDTO extends SerializableInlineObject {
  @JsonProperty("t")
  private String ticketId;

  @JsonProperty("p")
  private int page;

  public TicketViewDTO() {
    super(SerializableInlineType.TICKET_VIEW);
  }

  public TicketViewDTO(String ticketId, int page) {
    this();
    this.ticketId = ticketId;
    this.page = page;
  }
}
