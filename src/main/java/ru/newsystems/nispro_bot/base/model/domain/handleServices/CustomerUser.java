
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
    "UserFirstname",
    "UserID",
    "UserEmail",
    "CustomerCompanyName",
    "UserCountry",
    "UserCustomerID",
    "UserLoginFailed",
    "UserRefreshTime",
    "UserPhone",
    "CreateTime",
    "ValidID",
    "UserMobile",
    "UserLogin",
    "UserShowTickets",
    "CreateBy",
    "CustomerCompanyValidID",
    "UserCity",
    "CustomerCompanyStreet",
    "UserLastViewsTypes",
    "UserFax",
    "UserLastViewsLimit",
    "CustomerCompanyCity",
    "UserLastLogin",
    "UserLastname",
    "UserComment",
    "UserStreet",
    "Source",
    "CustomerCompanyURL",
    "UserTimeZone",
    "UserMailString",
    "ChangeBy",
    "UserLastLoginTimestamp",
    "UserLanguage",
    "CustomerCompanyCountry",
    "UserZip",
    "UserTitle",
    "CustomerID",
    "CustomerCompanyZIP",
    "UserFullname",
    "CustomerCompanyComment",
    "ChangeTime"
})
@Generated("jsonschema2pojo")
public class CustomerUser {

    @JsonProperty("UserFirstname")
    private String userFirstname;
    @JsonProperty("UserID")
    private String userID;
    @JsonProperty("UserEmail")
    private String userEmail;
    @JsonProperty("CustomerCompanyName")
    private String customerCompanyName;
    @JsonProperty("UserCountry")
    private String userCountry;
    @JsonProperty("UserCustomerID")
    private String userCustomerID;
    @JsonProperty("UserLoginFailed")
    private String userLoginFailed;
    @JsonProperty("UserRefreshTime")
    private String userRefreshTime;
    @JsonProperty("UserPhone")
    private String userPhone;
    @JsonProperty("CreateTime")
    private String createTime;
    @JsonProperty("ValidID")
    private Long validID;
    @JsonProperty("UserMobile")
    private String userMobile;
    @JsonProperty("UserLogin")
    private String userLogin;
    @JsonProperty("UserShowTickets")
    private String userShowTickets;
    @JsonProperty("CreateBy")
    private Long createBy;
    @JsonProperty("CustomerCompanyValidID")
    private Long customerCompanyValidID;
    @JsonProperty("UserCity")
    private String userCity;
    @JsonProperty("CustomerCompanyStreet")
    private String customerCompanyStreet;
    @JsonProperty("UserLastViewsTypes")
    private String userLastViewsTypes;
    @JsonProperty("UserFax")
    private String userFax;
    @JsonProperty("UserLastViewsLimit")
    private String userLastViewsLimit;
    @JsonProperty("CustomerCompanyCity")
    private String customerCompanyCity;
    @JsonProperty("UserLastLogin")
    private String userLastLogin;
    @JsonProperty("UserLastname")
    private String userLastname;
    @JsonProperty("UserComment")
    private String userComment;
    @JsonProperty("UserStreet")
    private String userStreet;
    @JsonProperty("Source")
    private String source;
    @JsonProperty("CustomerCompanyURL")
    private String customerCompanyURL;
    @JsonProperty("UserTimeZone")
    private String userTimeZone;
    @JsonProperty("UserMailString")
    private String userMailString;
    @JsonProperty("ChangeBy")
    private Long changeBy;
    @JsonProperty("UserLastLoginTimestamp")
    private String userLastLoginTimestamp;
    @JsonProperty("UserLanguage")
    private String userLanguage;
    @JsonProperty("CustomerCompanyCountry")
    private String customerCompanyCountry;
    @JsonProperty("UserZip")
    private String userZip;
    @JsonProperty("UserTitle")
    private String userTitle;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("CustomerCompanyZIP")
    private String customerCompanyZIP;
    @JsonProperty("UserFullname")
    private String userFullname;
    @JsonProperty("CustomerCompanyComment")
    private String customerCompanyComment;
    @JsonProperty("ChangeTime")
    private String changeTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("UserFirstname")
    public String getUserFirstname() {
        return userFirstname;
    }

    @JsonProperty("UserFirstname")
    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    @JsonProperty("UserID")
    public String getUserID() {
        return userID;
    }

    @JsonProperty("UserID")
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @JsonProperty("UserEmail")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("UserEmail")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("CustomerCompanyName")
    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    @JsonProperty("CustomerCompanyName")
    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    @JsonProperty("UserCountry")
    public String getUserCountry() {
        return userCountry;
    }

    @JsonProperty("UserCountry")
    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    @JsonProperty("UserCustomerID")
    public String getUserCustomerID() {
        return userCustomerID;
    }

    @JsonProperty("UserCustomerID")
    public void setUserCustomerID(String userCustomerID) {
        this.userCustomerID = userCustomerID;
    }

    @JsonProperty("UserLoginFailed")
    public String getUserLoginFailed() {
        return userLoginFailed;
    }

    @JsonProperty("UserLoginFailed")
    public void setUserLoginFailed(String userLoginFailed) {
        this.userLoginFailed = userLoginFailed;
    }

    @JsonProperty("UserRefreshTime")
    public String getUserRefreshTime() {
        return userRefreshTime;
    }

    @JsonProperty("UserRefreshTime")
    public void setUserRefreshTime(String userRefreshTime) {
        this.userRefreshTime = userRefreshTime;
    }

    @JsonProperty("UserPhone")
    public String getUserPhone() {
        return userPhone;
    }

    @JsonProperty("UserPhone")
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    @JsonProperty("UserMobile")
    public String getUserMobile() {
        return userMobile;
    }

    @JsonProperty("UserMobile")
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @JsonProperty("UserLogin")
    public String getUserLogin() {
        return userLogin;
    }

    @JsonProperty("UserLogin")
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @JsonProperty("UserShowTickets")
    public String getUserShowTickets() {
        return userShowTickets;
    }

    @JsonProperty("UserShowTickets")
    public void setUserShowTickets(String userShowTickets) {
        this.userShowTickets = userShowTickets;
    }

    @JsonProperty("CreateBy")
    public Long getCreateBy() {
        return createBy;
    }

    @JsonProperty("CreateBy")
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @JsonProperty("CustomerCompanyValidID")
    public Long getCustomerCompanyValidID() {
        return customerCompanyValidID;
    }

    @JsonProperty("CustomerCompanyValidID")
    public void setCustomerCompanyValidID(Long customerCompanyValidID) {
        this.customerCompanyValidID = customerCompanyValidID;
    }

    @JsonProperty("UserCity")
    public String getUserCity() {
        return userCity;
    }

    @JsonProperty("UserCity")
    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    @JsonProperty("CustomerCompanyStreet")
    public String getCustomerCompanyStreet() {
        return customerCompanyStreet;
    }

    @JsonProperty("CustomerCompanyStreet")
    public void setCustomerCompanyStreet(String customerCompanyStreet) {
        this.customerCompanyStreet = customerCompanyStreet;
    }

    @JsonProperty("UserLastViewsTypes")
    public String getUserLastViewsTypes() {
        return userLastViewsTypes;
    }

    @JsonProperty("UserLastViewsTypes")
    public void setUserLastViewsTypes(String userLastViewsTypes) {
        this.userLastViewsTypes = userLastViewsTypes;
    }

    @JsonProperty("UserFax")
    public String getUserFax() {
        return userFax;
    }

    @JsonProperty("UserFax")
    public void setUserFax(String userFax) {
        this.userFax = userFax;
    }

    @JsonProperty("UserLastViewsLimit")
    public String getUserLastViewsLimit() {
        return userLastViewsLimit;
    }

    @JsonProperty("UserLastViewsLimit")
    public void setUserLastViewsLimit(String userLastViewsLimit) {
        this.userLastViewsLimit = userLastViewsLimit;
    }

    @JsonProperty("CustomerCompanyCity")
    public String getCustomerCompanyCity() {
        return customerCompanyCity;
    }

    @JsonProperty("CustomerCompanyCity")
    public void setCustomerCompanyCity(String customerCompanyCity) {
        this.customerCompanyCity = customerCompanyCity;
    }

    @JsonProperty("UserLastLogin")
    public String getUserLastLogin() {
        return userLastLogin;
    }

    @JsonProperty("UserLastLogin")
    public void setUserLastLogin(String userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    @JsonProperty("UserLastname")
    public String getUserLastname() {
        return userLastname;
    }

    @JsonProperty("UserLastname")
    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    @JsonProperty("UserComment")
    public String getUserComment() {
        return userComment;
    }

    @JsonProperty("UserComment")
    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    @JsonProperty("UserStreet")
    public String getUserStreet() {
        return userStreet;
    }

    @JsonProperty("UserStreet")
    public void setUserStreet(String userStreet) {
        this.userStreet = userStreet;
    }

    @JsonProperty("Source")
    public String getSource() {
        return source;
    }

    @JsonProperty("Source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("CustomerCompanyURL")
    public String getCustomerCompanyURL() {
        return customerCompanyURL;
    }

    @JsonProperty("CustomerCompanyURL")
    public void setCustomerCompanyURL(String customerCompanyURL) {
        this.customerCompanyURL = customerCompanyURL;
    }

    @JsonProperty("UserTimeZone")
    public String getUserTimeZone() {
        return userTimeZone;
    }

    @JsonProperty("UserTimeZone")
    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    @JsonProperty("UserMailString")
    public String getUserMailString() {
        return userMailString;
    }

    @JsonProperty("UserMailString")
    public void setUserMailString(String userMailString) {
        this.userMailString = userMailString;
    }

    @JsonProperty("ChangeBy")
    public Long getChangeBy() {
        return changeBy;
    }

    @JsonProperty("ChangeBy")
    public void setChangeBy(Long changeBy) {
        this.changeBy = changeBy;
    }

    @JsonProperty("UserLastLoginTimestamp")
    public String getUserLastLoginTimestamp() {
        return userLastLoginTimestamp;
    }

    @JsonProperty("UserLastLoginTimestamp")
    public void setUserLastLoginTimestamp(String userLastLoginTimestamp) {
        this.userLastLoginTimestamp = userLastLoginTimestamp;
    }

    @JsonProperty("UserLanguage")
    public String getUserLanguage() {
        return userLanguage;
    }

    @JsonProperty("UserLanguage")
    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    @JsonProperty("CustomerCompanyCountry")
    public String getCustomerCompanyCountry() {
        return customerCompanyCountry;
    }

    @JsonProperty("CustomerCompanyCountry")
    public void setCustomerCompanyCountry(String customerCompanyCountry) {
        this.customerCompanyCountry = customerCompanyCountry;
    }

    @JsonProperty("UserZip")
    public String getUserZip() {
        return userZip;
    }

    @JsonProperty("UserZip")
    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    @JsonProperty("UserTitle")
    public String getUserTitle() {
        return userTitle;
    }

    @JsonProperty("UserTitle")
    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("CustomerCompanyZIP")
    public String getCustomerCompanyZIP() {
        return customerCompanyZIP;
    }

    @JsonProperty("CustomerCompanyZIP")
    public void setCustomerCompanyZIP(String customerCompanyZIP) {
        this.customerCompanyZIP = customerCompanyZIP;
    }

    @JsonProperty("UserFullname")
    public String getUserFullname() {
        return userFullname;
    }

    @JsonProperty("UserFullname")
    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    @JsonProperty("CustomerCompanyComment")
    public String getCustomerCompanyComment() {
        return customerCompanyComment;
    }

    @JsonProperty("CustomerCompanyComment")
    public void setCustomerCompanyComment(String customerCompanyComment) {
        this.customerCompanyComment = customerCompanyComment;
    }

    @JsonProperty("ChangeTime")
    public String getChangeTime() {
        return changeTime;
    }

    @JsonProperty("ChangeTime")
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
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
