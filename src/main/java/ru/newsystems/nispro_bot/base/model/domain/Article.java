package ru.newsystems.nispro_bot.base.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    @JsonProperty("ArticleID")
    private Long articleID;
    @JsonProperty("ArticleNumber")
    private Long articleNumber;
    @JsonProperty("Attachment")
    private List<Attachment> attachments;
    @JsonProperty("Bcc")
    private String bcc;
    @JsonProperty("Body")
    private String body;
    @JsonProperty("Cc")
    private String cc;
    @JsonProperty("ChangeBy")
    private Long changeBy;
    @JsonProperty("ChangeTime")
    private String changeTime;
    @JsonProperty("Charset")
    private String charset;
    @JsonProperty("CommunicationChannelID")
    private Long communicationChannelID;
    @JsonProperty("ContentCharset")
    private String contentCharset;
    @JsonProperty("ContentType")
    private String contentType;
    @JsonProperty("CreateBy")
    private Long createBy;
    @JsonProperty("CreateTime")
    private String createTime;
    @JsonProperty("From")
    private String from;
    @JsonProperty("InReplyTo")
    private String inReplyTo;
    @JsonProperty("IncomingTime")
    private Long incomingTime;
    @JsonProperty("IsVisibleForCustomer")
    private Long IiVisibleForCustomer;
    @JsonProperty("MessageID")
    private String messageID;
    @JsonProperty("MimeType")
    private String mimeType;
    @JsonProperty("References")
    private String references;
    @JsonProperty("ReplyTo")
    private String replyTo;
    @JsonProperty("SenderType")
    private String senderType;
    @JsonProperty("SenderTypeID")
    private String senderTypeID;
    @JsonProperty("Subject")
    private String subject;
    @JsonProperty("TicketID")
    private Long ticketID;
    @JsonProperty("TimeUnit")
    private Long timeUnit;
    @JsonProperty("To")
    private String to;
}
