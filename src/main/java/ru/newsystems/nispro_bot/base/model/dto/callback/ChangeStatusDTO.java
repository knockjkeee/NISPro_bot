package ru.newsystems.nispro_bot.base.model.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Getter
@Setter
public class ChangeStatusDTO extends SerializableInlineObject{

//    @JsonProperty("m")
//    private Long chatId;

    @JsonProperty("t")
    private String ticketId;

//    @JsonProperty("f")
//    private String from;

    public ChangeStatusDTO() {
        super(SerializableInlineType.CHANGE_STATUS);
    }

    public ChangeStatusDTO(String ticketId) {
        this();
        this.ticketId = ticketId;
    }

//    public ChangeStatusDTO(Long chatId, String ticketId, String from) {
//        this();
//        this.chatId = chatId;
//        this.ticketId = ticketId;
//        this.from = from;
//    }
}
