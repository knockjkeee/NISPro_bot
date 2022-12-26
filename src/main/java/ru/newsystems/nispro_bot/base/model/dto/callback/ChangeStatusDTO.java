package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Getter
@Setter
public class ChangeStatusDTO extends SerializableInlineObject{

    @JsonProperty("t")
    private String ticketId;

    @JsonProperty("d")
    private String direction;

    @JsonProperty("i")
    private String telegramId;

    public ChangeStatusDTO() {
        super(SerializableInlineType.CHANGE_STATUS);
    }

    public ChangeStatusDTO(String ticketId, String direction, String telegramId) {
        this();
        this.ticketId = ticketId;
        this.direction = direction;
        this.telegramId = telegramId;
    }
}
