package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Getter
@Setter
public class DownloadFilesDTO extends SerializableInlineObject {
  @JsonProperty("m")
  private Long chatId;

  @JsonProperty("a")
  private Long articleId;

  @JsonProperty("t")
  private String ticketId;

  @JsonProperty("f")
  private String from;


  public DownloadFilesDTO() {
    super(SerializableInlineType.DOWNLOAD);
  }

  public DownloadFilesDTO(Long chatId, String ticketId, Long articleId, String from) {
    this();
    this.chatId = chatId;
    this.ticketId = ticketId;
    this.articleId = articleId;
    this.from = from;
  }
}
