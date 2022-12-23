
package ru.newsystems.nispro_bot.base.model.domain.handleServices;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CreateTime",
    "ChangeBy",
    "SenderType",
    "DynamicField_ArticleFreeText1",
    "To",
    "InReplyTo",
    "ContentType",
    "ArticleID",
    "ArticleNumber",
    "Cc",
    "CreateBy",
    "ChangeTime",
    "MimeType",
    "ContentCharset",
    "TicketID",
    "ReplyTo",
    "Bcc",
    "References",
    "IsVisibleForCustomer",
    "Charset",
    "SenderTypeID",
    "FromRealname",
    "MessageID",
    "IncomingTime",
    "From",
    "Body",
    "CommunicationChannelID",
    "Subject"
})
@Generated("jsonschema2pojo")
public class Article__1 {

    @JsonProperty("CreateTime")
    private String createTime;
    @JsonProperty("ChangeBy")
    private Long changeBy;
    @JsonProperty("SenderType")
    private String senderType;
    @JsonProperty("DynamicField_ArticleFreeText1")
    private Object dynamicFieldArticleFreeText1;
    @JsonProperty("To")
    private String to;
    @JsonProperty("InReplyTo")
    private String inReplyTo;
    @JsonProperty("ContentType")
    private String contentType;
    @JsonProperty("ArticleID")
    private Long articleID;
    @JsonProperty("ArticleNumber")
    private Long articleNumber;
    @JsonProperty("Cc")
    private String cc;
    @JsonProperty("CreateBy")
    private Long createBy;
    @JsonProperty("ChangeTime")
    private String changeTime;
    @JsonProperty("MimeType")
    private String mimeType;
    @JsonProperty("ContentCharset")
    private String contentCharset;
    @JsonProperty("TicketID")
    private Long ticketID;
    @JsonProperty("ReplyTo")
    private String replyTo;
    @JsonProperty("Bcc")
    private String bcc;
    @JsonProperty("References")
    private String references;
    @JsonProperty("IsVisibleForCustomer")
    private Long isVisibleForCustomer;
    @JsonProperty("Charset")
    private String charset;
    @JsonProperty("SenderTypeID")
    private String senderTypeID;
    @JsonProperty("FromRealname")
    private String fromRealname;
    @JsonProperty("MessageID")
    private String messageID;
    @JsonProperty("IncomingTime")
    private Long incomingTime;
    @JsonProperty("From")
    private String from;
    @JsonProperty("Body")
    private String body;
    @JsonProperty("CommunicationChannelID")
    private Long communicationChannelID;
    @JsonProperty("Subject")
    private String subject;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CreateTime")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("CreateTime")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("ChangeBy")
    public Long getChangeBy() {
        return changeBy;
    }

    @JsonProperty("ChangeBy")
    public void setChangeBy(Long changeBy) {
        this.changeBy = changeBy;
    }

    @JsonProperty("SenderType")
    public String getSenderType() {
        return senderType;
    }

    @JsonProperty("SenderType")
    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    @JsonProperty("DynamicField_ArticleFreeText1")
    public Object getDynamicFieldArticleFreeText1() {
        return dynamicFieldArticleFreeText1;
    }

    @JsonProperty("DynamicField_ArticleFreeText1")
    public void setDynamicFieldArticleFreeText1(Object dynamicFieldArticleFreeText1) {
        this.dynamicFieldArticleFreeText1 = dynamicFieldArticleFreeText1;
    }

    @JsonProperty("To")
    public String getTo() {
        return to;
    }

    @JsonProperty("To")
    public void setTo(String to) {
        this.to = to;
    }

    @JsonProperty("InReplyTo")
    public String getInReplyTo() {
        return inReplyTo;
    }

    @JsonProperty("InReplyTo")
    public void setInReplyTo(String inReplyTo) {
        this.inReplyTo = inReplyTo;
    }

    @JsonProperty("ContentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("ContentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("ArticleID")
    public Long getArticleID() {
        return articleID;
    }

    @JsonProperty("ArticleID")
    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    @JsonProperty("ArticleNumber")
    public Long getArticleNumber() {
        return articleNumber;
    }

    @JsonProperty("ArticleNumber")
    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    @JsonProperty("Cc")
    public String getCc() {
        return cc;
    }

    @JsonProperty("Cc")
    public void setCc(String cc) {
        this.cc = cc;
    }

    @JsonProperty("CreateBy")
    public Long getCreateBy() {
        return createBy;
    }

    @JsonProperty("CreateBy")
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @JsonProperty("ChangeTime")
    public String getChangeTime() {
        return changeTime;
    }

    @JsonProperty("ChangeTime")
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    @JsonProperty("MimeType")
    public String getMimeType() {
        return mimeType;
    }

    @JsonProperty("MimeType")
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @JsonProperty("ContentCharset")
    public String getContentCharset() {
        return contentCharset;
    }

    @JsonProperty("ContentCharset")
    public void setContentCharset(String contentCharset) {
        this.contentCharset = contentCharset;
    }

    @JsonProperty("TicketID")
    public Long getTicketID() {
        return ticketID;
    }

    @JsonProperty("TicketID")
    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    @JsonProperty("ReplyTo")
    public String getReplyTo() {
        return replyTo;
    }

    @JsonProperty("ReplyTo")
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    @JsonProperty("Bcc")
    public String getBcc() {
        return bcc;
    }

    @JsonProperty("Bcc")
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    @JsonProperty("References")
    public String getReferences() {
        return references;
    }

    @JsonProperty("References")
    public void setReferences(String references) {
        this.references = references;
    }

    @JsonProperty("IsVisibleForCustomer")
    public Long getIsVisibleForCustomer() {
        return isVisibleForCustomer;
    }

    @JsonProperty("IsVisibleForCustomer")
    public void setIsVisibleForCustomer(Long isVisibleForCustomer) {
        this.isVisibleForCustomer = isVisibleForCustomer;
    }

    @JsonProperty("Charset")
    public String getCharset() {
        return charset;
    }

    @JsonProperty("Charset")
    public void setCharset(String charset) {
        this.charset = charset;
    }

    @JsonProperty("SenderTypeID")
    public String getSenderTypeID() {
        return senderTypeID;
    }

    @JsonProperty("SenderTypeID")
    public void setSenderTypeID(String senderTypeID) {
        this.senderTypeID = senderTypeID;
    }

    @JsonProperty("FromRealname")
    public String getFromRealname() {
        return fromRealname;
    }

    @JsonProperty("FromRealname")
    public void setFromRealname(String fromRealname) {
        this.fromRealname = fromRealname;
    }

    @JsonProperty("MessageID")
    public String getMessageID() {
        return messageID;
    }

    @JsonProperty("MessageID")
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    @JsonProperty("IncomingTime")
    public Long getIncomingTime() {
        return incomingTime;
    }

    @JsonProperty("IncomingTime")
    public void setIncomingTime(Long incomingTime) {
        this.incomingTime = incomingTime;
    }

    @JsonProperty("From")
    public String getFrom() {
        return from;
    }

    @JsonProperty("From")
    public void setFrom(String from) {
        this.from = from;
    }

    @JsonProperty("Body")
    public String getBody() {
        return body;
    }

    @JsonProperty("Body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("CommunicationChannelID")
    public Long getCommunicationChannelID() {
        return communicationChannelID;
    }

    @JsonProperty("CommunicationChannelID")
    public void setCommunicationChannelID(Long communicationChannelID) {
        this.communicationChannelID = communicationChannelID;
    }

    @JsonProperty("Subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("Subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
