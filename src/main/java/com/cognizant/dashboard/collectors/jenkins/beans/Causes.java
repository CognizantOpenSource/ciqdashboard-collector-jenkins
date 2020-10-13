package com.cognizant.dashboard.collectors.jenkins.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_class",
        "shortDescription",
        "userId",
        "userName"
})
@Data
public class Causes {
    @JsonProperty("_class")
    public String _class;

    @JsonProperty("shortDescription")
    public String shortDescription;

    @JsonProperty("userId")
    public String userId;

    @JsonProperty("userName")
    public String userName;
}
