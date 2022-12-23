
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
    "CustomerCompanyStreet",
    "ChangeTime",
    "CreateBy",
    "CustomerCompanyComment",
    "CustomerCompanyCountry",
    "CustomerCompanyZIP",
    "Source",
    "CustomerID",
    "CustomerCompanyName",
    "CustomerCompanyCity",
    "CustomerCompanyURL",
    "ChangeBy",
    "ValidID",
    "CreateTime"
})
@Generated("jsonschema2pojo")
public class CustomerCompany {

    @JsonProperty("CustomerCompanyStreet")
    private String customerCompanyStreet;
    @JsonProperty("ChangeTime")
    private String changeTime;
    @JsonProperty("CreateBy")
    private Long createBy;
    @JsonProperty("CustomerCompanyComment")
    private String customerCompanyComment;
    @JsonProperty("CustomerCompanyCountry")
    private String customerCompanyCountry;
    @JsonProperty("CustomerCompanyZIP")
    private String customerCompanyZIP;
    @JsonProperty("Source")
    private String source;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("CustomerCompanyName")
    private String customerCompanyName;
    @JsonProperty("CustomerCompanyCity")
    private String customerCompanyCity;
    @JsonProperty("CustomerCompanyURL")
    private String customerCompanyURL;
    @JsonProperty("ChangeBy")
    private Long changeBy;
    @JsonProperty("ValidID")
    private Long validID;
    @JsonProperty("CreateTime")
    private String createTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CustomerCompanyStreet")
    public String getCustomerCompanyStreet() {
        return customerCompanyStreet;
    }

    @JsonProperty("CustomerCompanyStreet")
    public void setCustomerCompanyStreet(String customerCompanyStreet) {
        this.customerCompanyStreet = customerCompanyStreet;
    }

    @JsonProperty("ChangeTime")
    public String getChangeTime() {
        return changeTime;
    }

    @JsonProperty("ChangeTime")
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    @JsonProperty("CreateBy")
    public Long getCreateBy() {
        return createBy;
    }

    @JsonProperty("CreateBy")
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @JsonProperty("CustomerCompanyComment")
    public String getCustomerCompanyComment() {
        return customerCompanyComment;
    }

    @JsonProperty("CustomerCompanyComment")
    public void setCustomerCompanyComment(String customerCompanyComment) {
        this.customerCompanyComment = customerCompanyComment;
    }

    @JsonProperty("CustomerCompanyCountry")
    public String getCustomerCompanyCountry() {
        return customerCompanyCountry;
    }

    @JsonProperty("CustomerCompanyCountry")
    public void setCustomerCompanyCountry(String customerCompanyCountry) {
        this.customerCompanyCountry = customerCompanyCountry;
    }

    @JsonProperty("CustomerCompanyZIP")
    public String getCustomerCompanyZIP() {
        return customerCompanyZIP;
    }

    @JsonProperty("CustomerCompanyZIP")
    public void setCustomerCompanyZIP(String customerCompanyZIP) {
        this.customerCompanyZIP = customerCompanyZIP;
    }

    @JsonProperty("Source")
    public String getSource() {
        return source;
    }

    @JsonProperty("Source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("CustomerCompanyName")
    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    @JsonProperty("CustomerCompanyName")
    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    @JsonProperty("CustomerCompanyCity")
    public String getCustomerCompanyCity() {
        return customerCompanyCity;
    }

    @JsonProperty("CustomerCompanyCity")
    public void setCustomerCompanyCity(String customerCompanyCity) {
        this.customerCompanyCity = customerCompanyCity;
    }

    @JsonProperty("CustomerCompanyURL")
    public String getCustomerCompanyURL() {
        return customerCompanyURL;
    }

    @JsonProperty("CustomerCompanyURL")
    public void setCustomerCompanyURL(String customerCompanyURL) {
        this.customerCompanyURL = customerCompanyURL;
    }

    @JsonProperty("ChangeBy")
    public Long getChangeBy() {
        return changeBy;
    }

    @JsonProperty("ChangeBy")
    public void setChangeBy(Long changeBy) {
        this.changeBy = changeBy;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
