
package ru.newsystems.nispro_bot.base.model.domain.handleServices;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "TicketNumber",
    "DynamicField_ITSMDecisionDate",
    "DynamicField_ProcessTimeUnits",
    "DynamicField_ProcessManagementProcessID",
    "PriorityData",
    "OwnerData",
    "Queue",
    "ServiceData",
    "DynamicField_ITSMCriticality",
    "Created",
    "CreateBy",
    "Age",
    "Articles",
    "StateID",
    "DynamicField_TicketFreeText1",
    "DynamicField_ITSMImpact",
    "ResponsibleData",
    "LockID",
    "Article",
    "CustomerUserID",
    "TypeID",
    "DynamicField_ITSMDecisionResult",
    "EscalationTime",
    "UntilTime",
    "PriorityID",
    "Lock",
    "Type",
    "SLAID",
    "DynamicField_ITSMReviewRequired",
    "OwnerID",
    "CustomerUser",
    "UnlockTimeout",
    "Changed",
    "DynamicField_TicketFreeText2",
    "ChangeBy",
    "EscalationUpdateTime",
    "TypeData",
    "EscalationSolutionTime",
    "Priority",
    "CustomerCompany",
    "CustomerID",
    "DynamicField_ProcessManagementActivityID",
    "StateType",
    "CreateByData",
    "ServiceID",
    "QueueID",
    "Responsible",
    "TicketID",
    "DynamicField_ITSMRecoveryStartTime",
    "SLAData",
    "EscalationResponseTime",
    "ResponsibleID",
    "State",
    "RealTillTimeNotUsed",
    "QueueData",
    "Title",
    "Owner",
    "FirstResponse",
    "DynamicField_Telegram",
    "ArchiveFlag",
    "DynamicField_ProcessManagementAttachment",
    "GroupID",
    "DynamicField_ITSMDueDate",
    "DynamicField_ITSMRepairStartTime"
})
@Generated("jsonschema2pojo")
public class Ticket {

    @JsonProperty("TicketNumber")
    private String ticketNumber;
    @JsonProperty("DynamicField_ITSMDecisionDate")
    private Object dynamicFieldITSMDecisionDate;
    @JsonProperty("DynamicField_ProcessTimeUnits")
    private Object dynamicFieldProcessTimeUnits;
    @JsonProperty("DynamicField_ProcessManagementProcessID")
    private Object dynamicFieldProcessManagementProcessID;
    @JsonProperty("PriorityData")
    private PriorityData priorityData;
    @JsonProperty("OwnerData")
    private OwnerData ownerData;
    @JsonProperty("Queue")
    private String queue;
    @JsonProperty("ServiceData")
    private ServiceData serviceData;
    @JsonProperty("DynamicField_ITSMCriticality")
    private Object dynamicFieldITSMCriticality;
    @JsonProperty("Created")
    private String created;
    @JsonProperty("CreateBy")
    private Long createBy;
    @JsonProperty("Age")
    private Long age;
    @JsonProperty("Articles")
    private List<Article> articles = null;
    @JsonProperty("StateID")
    private Long stateID;
    @JsonProperty("DynamicField_TicketFreeText1")
    private Object dynamicFieldTicketFreeText1;
    @JsonProperty("DynamicField_ITSMImpact")
    private Object dynamicFieldITSMImpact;
    @JsonProperty("ResponsibleData")
    private ResponsibleData responsibleData;
    @JsonProperty("LockID")
    private Long lockID;
    @JsonProperty("Article")
    private Article__1 article;
    @JsonProperty("CustomerUserID")
    private String customerUserID;
    @JsonProperty("TypeID")
    private Long typeID;
    @JsonProperty("DynamicField_ITSMDecisionResult")
    private Object dynamicFieldITSMDecisionResult;
    @JsonProperty("EscalationTime")
    private Long escalationTime;
    @JsonProperty("UntilTime")
    private Long untilTime;
    @JsonProperty("PriorityID")
    private Long priorityID;
    @JsonProperty("Lock")
    private String lock;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("SLAID")
    private String slaid;
    @JsonProperty("DynamicField_ITSMReviewRequired")
    private Object dynamicFieldITSMReviewRequired;
    @JsonProperty("OwnerID")
    private Long ownerID;
    @JsonProperty("CustomerUser")
    private CustomerUser customerUser;
    @JsonProperty("UnlockTimeout")
    private Long unlockTimeout;
    @JsonProperty("Changed")
    private String changed;
    @JsonProperty("DynamicField_TicketFreeText2")
    private Object dynamicFieldTicketFreeText2;
    @JsonProperty("ChangeBy")
    private Long changeBy;
    @JsonProperty("EscalationUpdateTime")
    private Long escalationUpdateTime;
    @JsonProperty("TypeData")
    private TypeData typeData;
    @JsonProperty("EscalationSolutionTime")
    private Long escalationSolutionTime;
    @JsonProperty("Priority")
    private String priority;
    @JsonProperty("CustomerCompany")
    private CustomerCompany customerCompany;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("DynamicField_ProcessManagementActivityID")
    private Object dynamicFieldProcessManagementActivityID;
    @JsonProperty("StateType")
    private String stateType;
    @JsonProperty("CreateByData")
    private CreateByData createByData;
    @JsonProperty("ServiceID")
    private String serviceID;
    @JsonProperty("QueueID")
    private Long queueID;
    @JsonProperty("Responsible")
    private String responsible;
    @JsonProperty("TicketID")
    private Long ticketID;
    @JsonProperty("DynamicField_ITSMRecoveryStartTime")
    private Object dynamicFieldITSMRecoveryStartTime;
    @JsonProperty("SLAData")
    private SLAData sLAData;
    @JsonProperty("EscalationResponseTime")
    private Long escalationResponseTime;
    @JsonProperty("ResponsibleID")
    private Long responsibleID;
    @JsonProperty("State")
    private String state;
    @JsonProperty("RealTillTimeNotUsed")
    private Long realTillTimeNotUsed;
    @JsonProperty("QueueData")
    private QueueData queueData;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Owner")
    private String owner;
    @JsonProperty("FirstResponse")
    private String firstResponse;
    @JsonProperty("DynamicField_Telegram")
    private String dynamicFieldTelegram;
    @JsonProperty("ArchiveFlag")
    private String archiveFlag;
    @JsonProperty("DynamicField_ProcessManagementAttachment")
    private Object dynamicFieldProcessManagementAttachment;
    @JsonProperty("GroupID")
    private Long groupID;
    @JsonProperty("DynamicField_ITSMDueDate")
    private Object dynamicFieldITSMDueDate;
    @JsonProperty("DynamicField_ITSMRepairStartTime")
    private Object dynamicFieldITSMRepairStartTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("TicketNumber")
    public String getTicketNumber() {
        return ticketNumber;
    }

    @JsonProperty("TicketNumber")
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @JsonProperty("DynamicField_ITSMDecisionDate")
    public Object getDynamicFieldITSMDecisionDate() {
        return dynamicFieldITSMDecisionDate;
    }

    @JsonProperty("DynamicField_ITSMDecisionDate")
    public void setDynamicFieldITSMDecisionDate(Object dynamicFieldITSMDecisionDate) {
        this.dynamicFieldITSMDecisionDate = dynamicFieldITSMDecisionDate;
    }

    @JsonProperty("DynamicField_ProcessTimeUnits")
    public Object getDynamicFieldProcessTimeUnits() {
        return dynamicFieldProcessTimeUnits;
    }

    @JsonProperty("DynamicField_ProcessTimeUnits")
    public void setDynamicFieldProcessTimeUnits(Object dynamicFieldProcessTimeUnits) {
        this.dynamicFieldProcessTimeUnits = dynamicFieldProcessTimeUnits;
    }

    @JsonProperty("DynamicField_ProcessManagementProcessID")
    public Object getDynamicFieldProcessManagementProcessID() {
        return dynamicFieldProcessManagementProcessID;
    }

    @JsonProperty("DynamicField_ProcessManagementProcessID")
    public void setDynamicFieldProcessManagementProcessID(Object dynamicFieldProcessManagementProcessID) {
        this.dynamicFieldProcessManagementProcessID = dynamicFieldProcessManagementProcessID;
    }

    @JsonProperty("PriorityData")
    public PriorityData getPriorityData() {
        return priorityData;
    }

    @JsonProperty("PriorityData")
    public void setPriorityData(PriorityData priorityData) {
        this.priorityData = priorityData;
    }

    @JsonProperty("OwnerData")
    public OwnerData getOwnerData() {
        return ownerData;
    }

    @JsonProperty("OwnerData")
    public void setOwnerData(OwnerData ownerData) {
        this.ownerData = ownerData;
    }

    @JsonProperty("Queue")
    public String getQueue() {
        return queue;
    }

    @JsonProperty("Queue")
    public void setQueue(String queue) {
        this.queue = queue;
    }

    @JsonProperty("ServiceData")
    public ServiceData getServiceData() {
        return serviceData;
    }

    @JsonProperty("ServiceData")
    public void setServiceData(ServiceData serviceData) {
        this.serviceData = serviceData;
    }

    @JsonProperty("DynamicField_ITSMCriticality")
    public Object getDynamicFieldITSMCriticality() {
        return dynamicFieldITSMCriticality;
    }

    @JsonProperty("DynamicField_ITSMCriticality")
    public void setDynamicFieldITSMCriticality(Object dynamicFieldITSMCriticality) {
        this.dynamicFieldITSMCriticality = dynamicFieldITSMCriticality;
    }

    @JsonProperty("Created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("Created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("CreateBy")
    public Long getCreateBy() {
        return createBy;
    }

    @JsonProperty("CreateBy")
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @JsonProperty("Age")
    public Long getAge() {
        return age;
    }

    @JsonProperty("Age")
    public void setAge(Long age) {
        this.age = age;
    }

    @JsonProperty("Articles")
    public List<Article> getArticles() {
        return articles;
    }

    @JsonProperty("Articles")
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @JsonProperty("StateID")
    public Long getStateID() {
        return stateID;
    }

    @JsonProperty("StateID")
    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }

    @JsonProperty("DynamicField_TicketFreeText1")
    public Object getDynamicFieldTicketFreeText1() {
        return dynamicFieldTicketFreeText1;
    }

    @JsonProperty("DynamicField_TicketFreeText1")
    public void setDynamicFieldTicketFreeText1(Object dynamicFieldTicketFreeText1) {
        this.dynamicFieldTicketFreeText1 = dynamicFieldTicketFreeText1;
    }

    @JsonProperty("DynamicField_ITSMImpact")
    public Object getDynamicFieldITSMImpact() {
        return dynamicFieldITSMImpact;
    }

    @JsonProperty("DynamicField_ITSMImpact")
    public void setDynamicFieldITSMImpact(Object dynamicFieldITSMImpact) {
        this.dynamicFieldITSMImpact = dynamicFieldITSMImpact;
    }

    @JsonProperty("ResponsibleData")
    public ResponsibleData getResponsibleData() {
        return responsibleData;
    }

    @JsonProperty("ResponsibleData")
    public void setResponsibleData(ResponsibleData responsibleData) {
        this.responsibleData = responsibleData;
    }

    @JsonProperty("LockID")
    public Long getLockID() {
        return lockID;
    }

    @JsonProperty("LockID")
    public void setLockID(Long lockID) {
        this.lockID = lockID;
    }

    @JsonProperty("Article")
    public Article__1 getArticle() {
        return article;
    }

    @JsonProperty("Article")
    public void setArticle(Article__1 article) {
        this.article = article;
    }

    @JsonProperty("CustomerUserID")
    public String getCustomerUserID() {
        return customerUserID;
    }

    @JsonProperty("CustomerUserID")
    public void setCustomerUserID(String customerUserID) {
        this.customerUserID = customerUserID;
    }

    @JsonProperty("TypeID")
    public Long getTypeID() {
        return typeID;
    }

    @JsonProperty("TypeID")
    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    @JsonProperty("DynamicField_ITSMDecisionResult")
    public Object getDynamicFieldITSMDecisionResult() {
        return dynamicFieldITSMDecisionResult;
    }

    @JsonProperty("DynamicField_ITSMDecisionResult")
    public void setDynamicFieldITSMDecisionResult(Object dynamicFieldITSMDecisionResult) {
        this.dynamicFieldITSMDecisionResult = dynamicFieldITSMDecisionResult;
    }

    @JsonProperty("EscalationTime")
    public Long getEscalationTime() {
        return escalationTime;
    }

    @JsonProperty("EscalationTime")
    public void setEscalationTime(Long escalationTime) {
        this.escalationTime = escalationTime;
    }

    @JsonProperty("UntilTime")
    public Long getUntilTime() {
        return untilTime;
    }

    @JsonProperty("UntilTime")
    public void setUntilTime(Long untilTime) {
        this.untilTime = untilTime;
    }

    @JsonProperty("PriorityID")
    public Long getPriorityID() {
        return priorityID;
    }

    @JsonProperty("PriorityID")
    public void setPriorityID(Long priorityID) {
        this.priorityID = priorityID;
    }

    @JsonProperty("Lock")
    public String getLock() {
        return lock;
    }

    @JsonProperty("Lock")
    public void setLock(String lock) {
        this.lock = lock;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("SLAID")
    public String getSlaid() {
        return slaid;
    }

    @JsonProperty("SLAID")
    public void setSlaid(String slaid) {
        this.slaid = slaid;
    }

    @JsonProperty("DynamicField_ITSMReviewRequired")
    public Object getDynamicFieldITSMReviewRequired() {
        return dynamicFieldITSMReviewRequired;
    }

    @JsonProperty("DynamicField_ITSMReviewRequired")
    public void setDynamicFieldITSMReviewRequired(Object dynamicFieldITSMReviewRequired) {
        this.dynamicFieldITSMReviewRequired = dynamicFieldITSMReviewRequired;
    }

    @JsonProperty("OwnerID")
    public Long getOwnerID() {
        return ownerID;
    }

    @JsonProperty("OwnerID")
    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    @JsonProperty("CustomerUser")
    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    @JsonProperty("CustomerUser")
    public void setCustomerUser(CustomerUser customerUser) {
        this.customerUser = customerUser;
    }

    @JsonProperty("UnlockTimeout")
    public Long getUnlockTimeout() {
        return unlockTimeout;
    }

    @JsonProperty("UnlockTimeout")
    public void setUnlockTimeout(Long unlockTimeout) {
        this.unlockTimeout = unlockTimeout;
    }

    @JsonProperty("Changed")
    public String getChanged() {
        return changed;
    }

    @JsonProperty("Changed")
    public void setChanged(String changed) {
        this.changed = changed;
    }

    @JsonProperty("DynamicField_TicketFreeText2")
    public Object getDynamicFieldTicketFreeText2() {
        return dynamicFieldTicketFreeText2;
    }

    @JsonProperty("DynamicField_TicketFreeText2")
    public void setDynamicFieldTicketFreeText2(Object dynamicFieldTicketFreeText2) {
        this.dynamicFieldTicketFreeText2 = dynamicFieldTicketFreeText2;
    }

    @JsonProperty("ChangeBy")
    public Long getChangeBy() {
        return changeBy;
    }

    @JsonProperty("ChangeBy")
    public void setChangeBy(Long changeBy) {
        this.changeBy = changeBy;
    }

    @JsonProperty("EscalationUpdateTime")
    public Long getEscalationUpdateTime() {
        return escalationUpdateTime;
    }

    @JsonProperty("EscalationUpdateTime")
    public void setEscalationUpdateTime(Long escalationUpdateTime) {
        this.escalationUpdateTime = escalationUpdateTime;
    }

    @JsonProperty("TypeData")
    public TypeData getTypeData() {
        return typeData;
    }

    @JsonProperty("TypeData")
    public void setTypeData(TypeData typeData) {
        this.typeData = typeData;
    }

    @JsonProperty("EscalationSolutionTime")
    public Long getEscalationSolutionTime() {
        return escalationSolutionTime;
    }

    @JsonProperty("EscalationSolutionTime")
    public void setEscalationSolutionTime(Long escalationSolutionTime) {
        this.escalationSolutionTime = escalationSolutionTime;
    }

    @JsonProperty("Priority")
    public String getPriority() {
        return priority;
    }

    @JsonProperty("Priority")
    public void setPriority(String priority) {
        this.priority = priority;
    }

    @JsonProperty("CustomerCompany")
    public CustomerCompany getCustomerCompany() {
        return customerCompany;
    }

    @JsonProperty("CustomerCompany")
    public void setCustomerCompany(CustomerCompany customerCompany) {
        this.customerCompany = customerCompany;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("DynamicField_ProcessManagementActivityID")
    public Object getDynamicFieldProcessManagementActivityID() {
        return dynamicFieldProcessManagementActivityID;
    }

    @JsonProperty("DynamicField_ProcessManagementActivityID")
    public void setDynamicFieldProcessManagementActivityID(Object dynamicFieldProcessManagementActivityID) {
        this.dynamicFieldProcessManagementActivityID = dynamicFieldProcessManagementActivityID;
    }

    @JsonProperty("StateType")
    public String getStateType() {
        return stateType;
    }

    @JsonProperty("StateType")
    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    @JsonProperty("CreateByData")
    public CreateByData getCreateByData() {
        return createByData;
    }

    @JsonProperty("CreateByData")
    public void setCreateByData(CreateByData createByData) {
        this.createByData = createByData;
    }

    @JsonProperty("ServiceID")
    public String getServiceID() {
        return serviceID;
    }

    @JsonProperty("ServiceID")
    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    @JsonProperty("QueueID")
    public Long getQueueID() {
        return queueID;
    }

    @JsonProperty("QueueID")
    public void setQueueID(Long queueID) {
        this.queueID = queueID;
    }

    @JsonProperty("Responsible")
    public String getResponsible() {
        return responsible;
    }

    @JsonProperty("Responsible")
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    @JsonProperty("TicketID")
    public Long getTicketID() {
        return ticketID;
    }

    @JsonProperty("TicketID")
    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    @JsonProperty("DynamicField_ITSMRecoveryStartTime")
    public Object getDynamicFieldITSMRecoveryStartTime() {
        return dynamicFieldITSMRecoveryStartTime;
    }

    @JsonProperty("DynamicField_ITSMRecoveryStartTime")
    public void setDynamicFieldITSMRecoveryStartTime(Object dynamicFieldITSMRecoveryStartTime) {
        this.dynamicFieldITSMRecoveryStartTime = dynamicFieldITSMRecoveryStartTime;
    }

    @JsonProperty("SLAData")
    public SLAData getSLAData() {
        return sLAData;
    }

    @JsonProperty("SLAData")
    public void setSLAData(SLAData sLAData) {
        this.sLAData = sLAData;
    }

    @JsonProperty("EscalationResponseTime")
    public Long getEscalationResponseTime() {
        return escalationResponseTime;
    }

    @JsonProperty("EscalationResponseTime")
    public void setEscalationResponseTime(Long escalationResponseTime) {
        this.escalationResponseTime = escalationResponseTime;
    }

    @JsonProperty("ResponsibleID")
    public Long getResponsibleID() {
        return responsibleID;
    }

    @JsonProperty("ResponsibleID")
    public void setResponsibleID(Long responsibleID) {
        this.responsibleID = responsibleID;
    }

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("RealTillTimeNotUsed")
    public Long getRealTillTimeNotUsed() {
        return realTillTimeNotUsed;
    }

    @JsonProperty("RealTillTimeNotUsed")
    public void setRealTillTimeNotUsed(Long realTillTimeNotUsed) {
        this.realTillTimeNotUsed = realTillTimeNotUsed;
    }

    @JsonProperty("QueueData")
    public QueueData getQueueData() {
        return queueData;
    }

    @JsonProperty("QueueData")
    public void setQueueData(QueueData queueData) {
        this.queueData = queueData;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("Owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @JsonProperty("FirstResponse")
    public String getFirstResponse() {
        return firstResponse;
    }

    @JsonProperty("FirstResponse")
    public void setFirstResponse(String firstResponse) {
        this.firstResponse = firstResponse;
    }

    @JsonProperty("DynamicField_Telegram")
    public String getDynamicFieldTelegram() {
        return dynamicFieldTelegram;
    }

    @JsonProperty("DynamicField_Telegram")
    public void setDynamicFieldTelegram(String dynamicFieldTelegram) {
        this.dynamicFieldTelegram = dynamicFieldTelegram;
    }

    @JsonProperty("ArchiveFlag")
    public String getArchiveFlag() {
        return archiveFlag;
    }

    @JsonProperty("ArchiveFlag")
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    @JsonProperty("DynamicField_ProcessManagementAttachment")
    public Object getDynamicFieldProcessManagementAttachment() {
        return dynamicFieldProcessManagementAttachment;
    }

    @JsonProperty("DynamicField_ProcessManagementAttachment")
    public void setDynamicFieldProcessManagementAttachment(Object dynamicFieldProcessManagementAttachment) {
        this.dynamicFieldProcessManagementAttachment = dynamicFieldProcessManagementAttachment;
    }

    @JsonProperty("GroupID")
    public Long getGroupID() {
        return groupID;
    }

    @JsonProperty("GroupID")
    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    @JsonProperty("DynamicField_ITSMDueDate")
    public Object getDynamicFieldITSMDueDate() {
        return dynamicFieldITSMDueDate;
    }

    @JsonProperty("DynamicField_ITSMDueDate")
    public void setDynamicFieldITSMDueDate(Object dynamicFieldITSMDueDate) {
        this.dynamicFieldITSMDueDate = dynamicFieldITSMDueDate;
    }

    @JsonProperty("DynamicField_ITSMRepairStartTime")
    public Object getDynamicFieldITSMRepairStartTime() {
        return dynamicFieldITSMRepairStartTime;
    }

    @JsonProperty("DynamicField_ITSMRepairStartTime")
    public void setDynamicFieldITSMRepairStartTime(Object dynamicFieldITSMRepairStartTime) {
        this.dynamicFieldITSMRepairStartTime = dynamicFieldITSMRepairStartTime;
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
