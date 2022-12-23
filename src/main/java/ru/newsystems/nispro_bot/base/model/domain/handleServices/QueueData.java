
package ru.newsystems.nispro_bot.base.model.domain.handleServices;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "FirstResponseNotify",
    "FirstResponseTime",
    "UpdateTime",
    "Name",
    "SystemAddressID",
    "Email",
    "RealName",
    "GroupID",
    "DefaultSignKey",
    "SalutationID",
    "SolutionTime",
    "UpdateNotify",
    "SolutionNotify",
    "FollowUpID",
    "UnlockTimeout",
    "ValidID",
    "CreateTime",
    "SignatureID",
    "FollowUpLock",
    "ChangeTime",
    "QueueID",
    "Calendar",
    "Comment"
})
@Generated("jsonschema2pojo")
public class QueueData {

    @JsonProperty("FirstResponseNotify")
    private Long firstResponseNotify;
    @JsonProperty("FirstResponseTime")
    private Long firstResponseTime;
    @JsonProperty("UpdateTime")
    private Long updateTime;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("SystemAddressID")
    private Long systemAddressID;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("RealName")
    private String realName;
    @JsonProperty("GroupID")
    private Long groupID;
    @JsonProperty("DefaultSignKey")
    private String defaultSignKey;
    @JsonProperty("SalutationID")
    private Long salutationID;
    @JsonProperty("SolutionTime")
    private Long solutionTime;
    @JsonProperty("UpdateNotify")
    private Long updateNotify;
    @JsonProperty("SolutionNotify")
    private Long solutionNotify;
    @JsonProperty("FollowUpID")
    private Long followUpID;
    @JsonProperty("UnlockTimeout")
    private Long unlockTimeout;
    @JsonProperty("ValidID")
    private Long validID;
    @JsonProperty("CreateTime")
    private String createTime;
    @JsonProperty("SignatureID")
    private Long signatureID;
    @JsonProperty("FollowUpLock")
    private Long followUpLock;
    @JsonProperty("ChangeTime")
    private String changeTime;
    @JsonProperty("QueueID")
    private Long queueID;
    @JsonProperty("Calendar")
    private String calendar;
    @JsonProperty("Comment")
    private String comment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("FirstResponseNotify")
    public Long getFirstResponseNotify() {
        return firstResponseNotify;
    }

    @JsonProperty("FirstResponseNotify")
    public void setFirstResponseNotify(Long firstResponseNotify) {
        this.firstResponseNotify = firstResponseNotify;
    }

    @JsonProperty("FirstResponseTime")
    public Long getFirstResponseTime() {
        return firstResponseTime;
    }

    @JsonProperty("FirstResponseTime")
    public void setFirstResponseTime(Long firstResponseTime) {
        this.firstResponseTime = firstResponseTime;
    }

    @JsonProperty("UpdateTime")
    public Long getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("UpdateTime")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("SystemAddressID")
    public Long getSystemAddressID() {
        return systemAddressID;
    }

    @JsonProperty("SystemAddressID")
    public void setSystemAddressID(Long systemAddressID) {
        this.systemAddressID = systemAddressID;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("RealName")
    public String getRealName() {
        return realName;
    }

    @JsonProperty("RealName")
    public void setRealName(String realName) {
        this.realName = realName;
    }

    @JsonProperty("GroupID")
    public Long getGroupID() {
        return groupID;
    }

    @JsonProperty("GroupID")
    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    @JsonProperty("DefaultSignKey")
    public String getDefaultSignKey() {
        return defaultSignKey;
    }

    @JsonProperty("DefaultSignKey")
    public void setDefaultSignKey(String defaultSignKey) {
        this.defaultSignKey = defaultSignKey;
    }

    @JsonProperty("SalutationID")
    public Long getSalutationID() {
        return salutationID;
    }

    @JsonProperty("SalutationID")
    public void setSalutationID(Long salutationID) {
        this.salutationID = salutationID;
    }

    @JsonProperty("SolutionTime")
    public Long getSolutionTime() {
        return solutionTime;
    }

    @JsonProperty("SolutionTime")
    public void setSolutionTime(Long solutionTime) {
        this.solutionTime = solutionTime;
    }

    @JsonProperty("UpdateNotify")
    public Long getUpdateNotify() {
        return updateNotify;
    }

    @JsonProperty("UpdateNotify")
    public void setUpdateNotify(Long updateNotify) {
        this.updateNotify = updateNotify;
    }

    @JsonProperty("SolutionNotify")
    public Long getSolutionNotify() {
        return solutionNotify;
    }

    @JsonProperty("SolutionNotify")
    public void setSolutionNotify(Long solutionNotify) {
        this.solutionNotify = solutionNotify;
    }

    @JsonProperty("FollowUpID")
    public Long getFollowUpID() {
        return followUpID;
    }

    @JsonProperty("FollowUpID")
    public void setFollowUpID(Long followUpID) {
        this.followUpID = followUpID;
    }

    @JsonProperty("UnlockTimeout")
    public Long getUnlockTimeout() {
        return unlockTimeout;
    }

    @JsonProperty("UnlockTimeout")
    public void setUnlockTimeout(Long unlockTimeout) {
        this.unlockTimeout = unlockTimeout;
    }

    @JsonProperty("ValidID")
    public Long getValidID() {
        return validID;
    }

    @JsonProperty("ValidID")
    public void setValidID(Long validID) {
        this.validID = validID;
    }

    @JsonProperty("CreateTime")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("CreateTime")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("SignatureID")
    public Long getSignatureID() {
        return signatureID;
    }

    @JsonProperty("SignatureID")
    public void setSignatureID(Long signatureID) {
        this.signatureID = signatureID;
    }

    @JsonProperty("FollowUpLock")
    public Long getFollowUpLock() {
        return followUpLock;
    }

    @JsonProperty("FollowUpLock")
    public void setFollowUpLock(Long followUpLock) {
        this.followUpLock = followUpLock;
    }

    @JsonProperty("ChangeTime")
    public String getChangeTime() {
        return changeTime;
    }

    @JsonProperty("ChangeTime")
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    @JsonProperty("QueueID")
    public Long getQueueID() {
        return queueID;
    }

    @JsonProperty("QueueID")
    public void setQueueID(Long queueID) {
        this.queueID = queueID;
    }

    @JsonProperty("Calendar")
    public String getCalendar() {
        return calendar;
    }

    @JsonProperty("Calendar")
    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    @JsonProperty("Comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("Comment")
    public void setComment(String comment) {
        this.comment = comment;
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
