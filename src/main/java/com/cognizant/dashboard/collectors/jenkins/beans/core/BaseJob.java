package com.cognizant.dashboard.collectors.jenkins.beans.core;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "url",
        "color"
})
@Data
public class BaseJob {

    @JsonProperty("name")
    private String jobName;
    @JsonProperty("url")
    private String jobUrl;
    @JsonProperty("color")
    private String color;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
