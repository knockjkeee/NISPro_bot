package ru.newsystems.nispro_bot.base.model.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketJ {

    @JsonProperty("Age")
    private Long age;
    @JsonProperty("ArchiveFlag")
    private String archiveFlag;
    @JsonProperty("Article")
    private List<Article> articles;
    @JsonProperty("ChangeBy")
    private Long changeBy;
    @JsonProperty("Changed")
    private String changed;
    @JsonProperty("CreateBy")
    private Long createBy;
    @JsonProperty("Created")
    private String created;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("CustomerUserID")
    private String customerUserID;
    @JsonProperty("EscalationResponseTime")
    private Long escalationResponseTime;
    @JsonProperty("EscalationSolutionTime")
    private Long escalationSolutionTime;
    @JsonProperty("EscalationTime")
    private Long escalationTime;
    @JsonProperty("EscalationUpdateTime")
    private Long escalationUpdateTime;
    @JsonProperty("FirstLock")
    private String firstLock;
    @JsonProperty("FirstResponse")
    private String firstResponse;
    @JsonProperty("GroupID")
    private Long groupID;
    @JsonProperty("Lock")
    private String lock;
    @JsonProperty("LockID")
    private Long lockID;
    @JsonProperty("Owner")
    private String owner;
    @JsonProperty("OwnerID")
    private Long ownerID;
    @JsonProperty("Priority")
    private String priority;
    @JsonProperty("PriorityID")
    private Long priorityID;
    @JsonProperty("Queue")
    private String queue;
    @JsonProperty("QueueID")
    private Long queueID;
    @JsonProperty("RealTillTimeNotUsed")
    private Long realTillTimeNotUsed;
    @JsonProperty("Responsible")
    private String responsible;
    @JsonProperty("ResponsibleID")
    private Long responsibleID;
    @JsonProperty("SLAID")
    private String slaid;
    @JsonProperty("ServiceID")
    private String serviceID;
    @JsonProperty("State")
    private String state;
    @JsonProperty("StateID")
    private Long stateID;
    @JsonProperty("StateType")
    private String stateType;
    @JsonProperty("TicketID")
    private Long ticketID;
    @JsonProperty("TicketNumber")
    private String ticketNumber;
    @JsonProperty("TimeUnit")
    private Long timeUnit;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("TypeID")
    private Long typeID;
    @JsonProperty("UnlockTimeout")
    private Long unlockTimeout;
    @JsonProperty("UntilTime")
    private Long untilTime;

}
