
package ru.newsystems.nispro_bot.base.model.domain.handleServices;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Name",
    "ValidID",
    "CreateTime",
    "ChangeBy",
    "ID",
    "CreateBy",
    "ChangeTime"
})
@Generated("jsonschema2pojo")
public class TypeData {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("ValidID")
    private Long validID;
    @JsonProperty("CreateTime")
    private String createTime;
    @JsonProperty("ChangeBy")
    private Long changeBy;
    @JsonProperty("ID")
    private Long id;
    @JsonProperty("CreateBy")
    private Long createBy;
    @JsonProperty("ChangeTime")
    private String changeTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
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

    @JsonProperty("ChangeBy")
    public Long getChangeBy() {
        return changeBy;
    }

    @JsonProperty("ChangeBy")
    public void setChangeBy(Long changeBy) {
        this.changeBy = changeBy;
    }

    @JsonProperty("ID")
    public Long getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(Long id) {
        this.id = id;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
