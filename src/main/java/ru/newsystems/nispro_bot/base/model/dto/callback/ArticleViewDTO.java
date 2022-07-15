package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Getter
@Setter
public class ArticleViewDTO extends SerializableInlineObject {
  @JsonProperty("a")
  private String articleId;

  @JsonProperty("p")
  private int page;

  public ArticleViewDTO() {
    super(SerializableInlineType.ARTICLE_VIEW);
  }

  public ArticleViewDTO(String articleId, int page) {
    this();
    this.articleId = articleId;
    this.page = page;
  }
}
