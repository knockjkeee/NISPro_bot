
package ru.newsystems.nispro_bot.base.model.domain.handleServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "SenderTypeID",
    "Recipients",
    "TicketID",
    "SkipRecipients",
    "CustomerMessageParams",
    "Queue",
    "Event",
    "IsVisibleForCustomer",
    "ArticleID"
})
@Generated("jsonschema2pojo")
public class Event {

    @JsonProperty("SenderTypeID")
    private String senderTypeID;
    @JsonProperty("Recipients")
    private Object recipients;
    @JsonProperty("TicketID")
    private String ticketID;
    @JsonProperty("SkipRecipients")
    private List<Object> skipRecipients = null;
    @JsonProperty("CustomerMessageParams")
    private CustomerMessageParams customerMessageParams;
    @JsonProperty("Queue")
    private Object queue;
    @JsonProperty("Event")
    private String event;
    @JsonProperty("IsVisibleForCustomer")
    private String isVisibleForCustomer;
    @JsonProperty("ArticleID")
    private Long articleID;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("SenderTypeID")
    public String getSenderTypeID() {
        return senderTypeID;
    }

    @JsonProperty("SenderTypeID")
    public void setSenderTypeID(String senderTypeID) {
        this.senderTypeID = senderTypeID;
    }

    @JsonProperty("Recipients")
    public Object getRecipients() {
        return recipients;
    }

    @JsonProperty("Recipients")
    public void setRecipients(Object recipients) {
        this.recipients = recipients;
    }

    @JsonProperty("TicketID")
    public String getTicketID() {
        return ticketID;
    }

    @JsonProperty("TicketID")
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    @JsonProperty("SkipRecipients")
    public List<Object> getSkipRecipients() {
        return skipRecipients;
    }

    @JsonProperty("SkipRecipients")
    public void setSkipRecipients(List<Object> skipRecipients) {
        this.skipRecipients = skipRecipients;
    }

    @JsonProperty("CustomerMessageParams")
    public CustomerMessageParams getCustomerMessageParams() {
        return customerMessageParams;
    }

    @JsonProperty("CustomerMessageParams")
    public void setCustomerMessageParams(CustomerMessageParams customerMessageParams) {
        this.customerMessageParams = customerMessageParams;
    }

    @JsonProperty("Queue")
    public Object getQueue() {
        return queue;
    }

    @JsonProperty("Queue")
    public void setQueue(Object queue) {
        this.queue = queue;
    }

    @JsonProperty("Event")
    public String getEvent() {
        return event;
    }

    @JsonProperty("Event")
    public void setEvent(String event) {
        this.event = event;
    }

    @JsonProperty("IsVisibleForCustomer")
    public String getIsVisibleForCustomer() {
        return isVisibleForCustomer;
    }

    @JsonProperty("IsVisibleForCustomer")
    public void setIsVisibleForCustomer(String isVisibleForCustomer) {
        this.isVisibleForCustomer = isVisibleForCustomer;
    }

    @JsonProperty("ArticleID")
    public Long getArticleID() {
        return articleID;
    }

    @JsonProperty("ArticleID")
    public void setArticleID(Long articleID) {
        this.articleID = articleID;
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
