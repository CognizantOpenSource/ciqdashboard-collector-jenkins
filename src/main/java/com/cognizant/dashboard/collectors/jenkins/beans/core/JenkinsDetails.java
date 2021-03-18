/*
 * © [2021] Cognizant. All rights reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.cognizant.dashboard.collectors.jenkins.beans.core;

import com.cognizant.dashboard.collectors.jenkins.beans.jobs.*;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * JenkinsDetails - JobDetails Pojo
 * @author Cognizant
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mode",
        "nodeDescription",
        "nodeName",
        "numExecutors",
        "description",
        "jobs",
        "overallLoad",
        "primaryView",
        "quietingDown",
        "slaveAgentPort",
        "unlabeledLoad",
        "url",
        "useCrumbs",
        "useSecurity",
        "views"
})
@Data
public class JenkinsDetails {

    @JsonProperty("mode")
    private String mode;
    @JsonProperty("nodeDescription")
    private String nodeDescription;
    @JsonProperty("nodeName")
    private String nodeName;
    @JsonProperty("numExecutors")
    private Integer numExecutors;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("jobs")
    private List<BaseJob> jobs = null;
    @JsonProperty("overallLoad")
    private OverallLoad overallLoad;
    @JsonProperty("primaryView")
    private PrimaryView primaryView;
    @JsonProperty("quietingDown")
    private Boolean quietingDown;
    @JsonProperty("slaveAgentPort")
    private Integer slaveAgentPort;
    @JsonProperty("unlabeledLoad")
    private UnlabeledLoad unlabeledLoad;
    @JsonProperty("url")
    private String url;
    @JsonProperty("useCrumbs")
    private Boolean useCrumbs;
    @JsonProperty("useSecurity")
    private Boolean useSecurity;
    @JsonProperty("views")
    private List<View> views = null;
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



