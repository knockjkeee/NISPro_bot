package ru.newsystems.nispro_bot.base.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment {
    @JsonProperty("Content")
    private String content;
    @JsonProperty("ContentAlternative")
    private String contentAlternative;
    @JsonProperty("ContentID")
    private String contentID;
    @JsonProperty("ContentType")
    private String contentType;
    @JsonProperty("Disposition")
    private String disposition;
    @JsonProperty("FileID")
    private String fileID;
    @JsonProperty("Filename")
    private String filename;
    @JsonProperty("FilesizeRaw")
    private String filesizeRaw;
}
