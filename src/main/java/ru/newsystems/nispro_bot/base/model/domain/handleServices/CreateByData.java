
package ru.newsystems.nispro_bot.base.model.domain.handleServices;

import java.util.HashMap;
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
    "UserLastViewsPosition",
    "UserSurveyOverviewSmallPageShown",
    "ChangeTime",
    "UserFullname",
    "UserLogin",
    "UserTitle",
    "UserLastLoginTimestamp",
    "UserCreateWorkOrderNextMask",
    "UserMobile",
    "UserFAQJournalOverviewSmallPageShown",
    "UserStoredFilterColumns-AgentTicketResponsibleView",
    "CreateTime",
    "ValidID",
    "UserTicketOverviewMediumPageShown",
    "UserRefreshTime",
    "UserTicketOverviewAgentTicketResponsibleView",
    "UserCreateNextMask",
    "UserLastname",
    "UserLastLogin",
    "AdminCommunicationLogPageShown",
    "UserLoginFailed",
    "UserFAQOverviewSmallPageShown",
    "UserChangeOverviewSmallPageShown",
    "UserTicketOverviewPreviewPageShown",
    "UserEmail",
    "AdminDynamicFieldsOverviewPageShown",
    "UserTicketOverviewAgentTicketSearch",
    "UserFirstname",
    "UserTicketOverviewSmallPageShown",
    "UserID",
    "UserLastViewsLimit"
})
@Generated("jsonschema2pojo")
public class CreateByData {

    @JsonProperty("UserLastViewsPosition")
    private String userLastViewsPosition;
    @JsonProperty("UserSurveyOverviewSmallPageShown")
    private String userSurveyOverviewSmallPageShown;
    @JsonProperty("ChangeTime")
    private String changeTime;
    @JsonProperty("UserFullname")
    private String userFullname;
    @JsonProperty("UserLogin")
    private String userLogin;
    @JsonProperty("UserTitle")
    private String userTitle;
    @JsonProperty("UserLastLoginTimestamp")
    private String userLastLoginTimestamp;
    @JsonProperty("UserCreateWorkOrderNextMask")
    private String userCreateWorkOrderNextMask;
    @JsonProperty("UserMobile")
    private String userMobile;
    @JsonProperty("UserFAQJournalOverviewSmallPageShown")
    private String userFAQJournalOverviewSmallPageShown;
    @JsonProperty("UserStoredFilterColumns-AgentTicketResponsibleView")
    private String userStoredFilterColumnsAgentTicketResponsibleView;
    @JsonProperty("CreateTime")
    private String createTime;
    @JsonProperty("ValidID")
    private Long validID;
    @JsonProperty("UserTicketOverviewMediumPageShown")
    private String userTicketOverviewMediumPageShown;
    @JsonProperty("UserRefreshTime")
    private String userRefreshTime;
    @JsonProperty("UserTicketOverviewAgentTicketResponsibleView")
    private String userTicketOverviewAgentTicketResponsibleView;
    @JsonProperty("UserCreateNextMask")
    private String userCreateNextMask;
    @JsonProperty("UserLastname")
    private String userLastname;
    @JsonProperty("UserLastLogin")
    private String userLastLogin;
    @JsonProperty("AdminCommunicationLogPageShown")
    private String adminCommunicationLogPageShown;
    @JsonProperty("UserLoginFailed")
    private String userLoginFailed;
    @JsonProperty("UserFAQOverviewSmallPageShown")
    private String userFAQOverviewSmallPageShown;
    @JsonProperty("UserChangeOverviewSmallPageShown")
    private String userChangeOverviewSmallPageShown;
    @JsonProperty("UserTicketOverviewPreviewPageShown")
    private String userTicketOverviewPreviewPageShown;
    @JsonProperty("UserEmail")
    private String userEmail;
    @JsonProperty("AdminDynamicFieldsOverviewPageShown")
    private String adminDynamicFieldsOverviewPageShown;
    @JsonProperty("UserTicketOverviewAgentTicketSearch")
    private String userTicketOverviewAgentTicketSearch;
    @JsonProperty("UserFirstname")
    private String userFirstname;
    @JsonProperty("UserTicketOverviewSmallPageShown")
    private String userTicketOverviewSmallPageShown;
    @JsonProperty("UserID")
    private Long userID;
    @JsonProperty("UserLastViewsLimit")
    private String userLastViewsLimit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("UserLastViewsPosition")
    public String getUserLastViewsPosition() {
        return userLastViewsPosition;
    }

    @JsonProperty("UserLastViewsPosition")
    public void setUserLastViewsPosition(String userLastViewsPosition) {
        this.userLastViewsPosition = userLastViewsPosition;
    }

    @JsonProperty("UserSurveyOverviewSmallPageShown")
    public String getUserSurveyOverviewSmallPageShown() {
        return userSurveyOverviewSmallPageShown;
    }

    @JsonProperty("UserSurveyOverviewSmallPageShown")
    public void setUserSurveyOverviewSmallPageShown(String userSurveyOverviewSmallPageShown) {
        this.userSurveyOverviewSmallPageShown = userSurveyOverviewSmallPageShown;
    }

    @JsonProperty("ChangeTime")
    public String getChangeTime() {
        return changeTime;
    }

    @JsonProperty("ChangeTime")
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    @JsonProperty("UserFullname")
    public String getUserFullname() {
        return userFullname;
    }

    @JsonProperty("UserFullname")
    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    @JsonProperty("UserLogin")
    public String getUserLogin() {
        return userLogin;
    }

    @JsonProperty("UserLogin")
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @JsonProperty("UserTitle")
    public String getUserTitle() {
        return userTitle;
    }

    @JsonProperty("UserTitle")
    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    @JsonProperty("UserLastLoginTimestamp")
    public String getUserLastLoginTimestamp() {
        return userLastLoginTimestamp;
    }

    @JsonProperty("UserLastLoginTimestamp")
    public void setUserLastLoginTimestamp(String userLastLoginTimestamp) {
        this.userLastLoginTimestamp = userLastLoginTimestamp;
    }

    @JsonProperty("UserCreateWorkOrderNextMask")
    public String getUserCreateWorkOrderNextMask() {
        return userCreateWorkOrderNextMask;
    }

    @JsonProperty("UserCreateWorkOrderNextMask")
    public void setUserCreateWorkOrderNextMask(String userCreateWorkOrderNextMask) {
        this.userCreateWorkOrderNextMask = userCreateWorkOrderNextMask;
    }

    @JsonProperty("UserMobile")
    public String getUserMobile() {
        return userMobile;
    }

    @JsonProperty("UserMobile")
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @JsonProperty("UserFAQJournalOverviewSmallPageShown")
    public String getUserFAQJournalOverviewSmallPageShown() {
        return userFAQJournalOverviewSmallPageShown;
    }

    @JsonProperty("UserFAQJournalOverviewSmallPageShown")
    public void setUserFAQJournalOverviewSmallPageShown(String userFAQJournalOverviewSmallPageShown) {
        this.userFAQJournalOverviewSmallPageShown = userFAQJournalOverviewSmallPageShown;
    }

    @JsonProperty("UserStoredFilterColumns-AgentTicketResponsibleView")
    public String getUserStoredFilterColumnsAgentTicketResponsibleView() {
        return userStoredFilterColumnsAgentTicketResponsibleView;
    }

    @JsonProperty("UserStoredFilterColumns-AgentTicketResponsibleView")
    public void setUserStoredFilterColumnsAgentTicketResponsibleView(String userStoredFilterColumnsAgentTicketResponsibleView) {
        this.userStoredFilterColumnsAgentTicketResponsibleView = userStoredFilterColumnsAgentTicketResponsibleView;
    }

    @JsonProperty("CreateTime")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("CreateTime")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("ValidID")
    public Long getValidID() {
        return validID;
    }

    @JsonProperty("ValidID")
    public void setValidID(Long validID) {
        this.validID = validID;
    }

    @JsonProperty("UserTicketOverviewMediumPageShown")
    public String getUserTicketOverviewMediumPageShown() {
        return userTicketOverviewMediumPageShown;
    }

    @JsonProperty("UserTicketOverviewMediumPageShown")
    public void setUserTicketOverviewMediumPageShown(String userTicketOverviewMediumPageShown) {
        this.userTicketOverviewMediumPageShown = userTicketOverviewMediumPageShown;
    }

    @JsonProperty("UserRefreshTime")
    public String getUserRefreshTime() {
        return userRefreshTime;
    }

    @JsonProperty("UserRefreshTime")
    public void setUserRefreshTime(String userRefreshTime) {
        this.userRefreshTime = userRefreshTime;
    }

    @JsonProperty("UserTicketOverviewAgentTicketResponsibleView")
    public String getUserTicketOverviewAgentTicketResponsibleView() {
        return userTicketOverviewAgentTicketResponsibleView;
    }

    @JsonProperty("UserTicketOverviewAgentTicketResponsibleView")
    public void setUserTicketOverviewAgentTicketResponsibleView(String userTicketOverviewAgentTicketResponsibleView) {
        this.userTicketOverviewAgentTicketResponsibleView = userTicketOverviewAgentTicketResponsibleView;
    }

    @JsonProperty("UserCreateNextMask")
    public String getUserCreateNextMask() {
        return userCreateNextMask;
    }

    @JsonProperty("UserCreateNextMask")
    public void setUserCreateNextMask(String userCreateNextMask) {
        this.userCreateNextMask = userCreateNextMask;
    }

    @JsonProperty("UserLastname")
    public String getUserLastname() {
        return userLastname;
    }

    @JsonProperty("UserLastname")
    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    @JsonProperty("UserLastLogin")
    public String getUserLastLogin() {
        return userLastLogin;
    }

    @JsonProperty("UserLastLogin")
    public void setUserLastLogin(String userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    @JsonProperty("AdminCommunicationLogPageShown")
    public String getAdminCommunicationLogPageShown() {
        return adminCommunicationLogPageShown;
    }

    @JsonProperty("AdminCommunicationLogPageShown")
    public void setAdminCommunicationLogPageShown(String adminCommunicationLogPageShown) {
        this.adminCommunicationLogPageShown = adminCommunicationLogPageShown;
    }

    @JsonProperty("UserLoginFailed")
    public String getUserLoginFailed() {
        return userLoginFailed;
    }

    @JsonProperty("UserLoginFailed")
    public void setUserLoginFailed(String userLoginFailed) {
        this.userLoginFailed = userLoginFailed;
    }

    @JsonProperty("UserFAQOverviewSmallPageShown")
    public String getUserFAQOverviewSmallPageShown() {
        return userFAQOverviewSmallPageShown;
    }

    @JsonProperty("UserFAQOverviewSmallPageShown")
    public void setUserFAQOverviewSmallPageShown(String userFAQOverviewSmallPageShown) {
        this.userFAQOverviewSmallPageShown = userFAQOverviewSmallPageShown;
    }

    @JsonProperty("UserChangeOverviewSmallPageShown")
    public String getUserChangeOverviewSmallPageShown() {
        return userChangeOverviewSmallPageShown;
    }

    @JsonProperty("UserChangeOverviewSmallPageShown")
    public void setUserChangeOverviewSmallPageShown(String userChangeOverviewSmallPageShown) {
        this.userChangeOverviewSmallPageShown = userChangeOverviewSmallPageShown;
    }

    @JsonProperty("UserTicketOverviewPreviewPageShown")
    public String getUserTicketOverviewPreviewPageShown() {
        return userTicketOverviewPreviewPageShown;
    }

    @JsonProperty("UserTicketOverviewPreviewPageShown")
    public void setUserTicketOverviewPreviewPageShown(String userTicketOverviewPreviewPageShown) {
        this.userTicketOverviewPreviewPageShown = userTicketOverviewPreviewPageShown;
    }

    @JsonProperty("UserEmail")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("UserEmail")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("AdminDynamicFieldsOverviewPageShown")
    public String getAdminDynamicFieldsOverviewPageShown() {
        return adminDynamicFieldsOverviewPageShown;
    }

    @JsonProperty("AdminDynamicFieldsOverviewPageShown")
    public void setAdminDynamicFieldsOverviewPageShown(String adminDynamicFieldsOverviewPageShown) {
        this.adminDynamicFieldsOverviewPageShown = adminDynamicFieldsOverviewPageShown;
    }

    @JsonProperty("UserTicketOverviewAgentTicketSearch")
    public String getUserTicketOverviewAgentTicketSearch() {
        return userTicketOverviewAgentTicketSearch;
    }

    @JsonProperty("UserTicketOverviewAgentTicketSearch")
    public void setUserTicketOverviewAgentTicketSearch(String userTicketOverviewAgentTicketSearch) {
        this.userTicketOverviewAgentTicketSearch = userTicketOverviewAgentTicketSearch;
    }

    @JsonProperty("UserFirstname")
    public String getUserFirstname() {
        return userFirstname;
    }

    @JsonProperty("UserFirstname")
    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    @JsonProperty("UserTicketOverviewSmallPageShown")
    public String getUserTicketOverviewSmallPageShown() {
        return userTicketOverviewSmallPageShown;
    }

    @JsonProperty("UserTicketOverviewSmallPageShown")
    public void setUserTicketOverviewSmallPageShown(String userTicketOverviewSmallPageShown) {
        this.userTicketOverviewSmallPageShown = userTicketOverviewSmallPageShown;
    }

    @JsonProperty("UserID")
    public Long getUserID() {
        return userID;
    }

    @JsonProperty("UserID")
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @JsonProperty("UserLastViewsLimit")
    public String getUserLastViewsLimit() {
        return userLastViewsLimit;
    }

    @JsonProperty("UserLastViewsLimit")
    public void setUserLastViewsLimit(String userLastViewsLimit) {
        this.userLastViewsLimit = userLastViewsLimit;
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
