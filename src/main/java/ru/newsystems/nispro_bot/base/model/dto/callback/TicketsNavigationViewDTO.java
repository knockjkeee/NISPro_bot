package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Getter
@Setter
public class TicketsNavigationViewDTO extends SerializableInlineObject {

  @JsonProperty("p")
  private int page;

  @JsonProperty("d")
  private String direction;


  public TicketsNavigationViewDTO() {
    super(SerializableInlineType.TICKETS_NAVIGATION);
  }

  public TicketsNavigationViewDTO(int page, String direction ) {
    this();
    this.page = page;
    this.direction = direction;
  }
}
