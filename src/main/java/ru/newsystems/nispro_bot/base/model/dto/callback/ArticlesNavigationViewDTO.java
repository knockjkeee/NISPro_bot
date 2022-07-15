package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Getter
@Setter
public class ArticlesNavigationViewDTO extends SerializableInlineObject {

  @JsonProperty("t")
  private String ticketNumber;

  @JsonProperty("p")
  private int page;

  @JsonProperty("d")
  private String direction;


  public ArticlesNavigationViewDTO() {
    super(SerializableInlineType.ARTICLE_NAVIGATION);
  }

  public ArticlesNavigationViewDTO(int page, String direction, String  ticketNumber) {
    this();
    this.page = page;
    this.direction = direction;
    this.ticketNumber = ticketNumber;
  }
}
