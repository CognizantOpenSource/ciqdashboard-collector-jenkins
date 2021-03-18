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


import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * JenkinsJob - Job description
 * @author Cognizant
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "description",
        "displayName",
        "displayNameOrNull",
        "fullDisplayName",
        "fullName",
        "name",
        "url",
        "buildable",
        "builds",
        "color",
        "firstBuild",
        "healthReport",
        "inQueue",
        "keepDependencies",
        "lastBuild",
        "lastCompletedBuild",
        "lastFailedBuild",
        "lastStableBuild",
        "lastSuccessfulBuild",
        "lastUnstableBuild",
        "lastUnsuccessfulBuild",
        "nextBuildNumber",
        "property",
        "queueItem",
        "concurrentBuild",
        "resumeBlocked"
})
public class JenkinsJob {
    @JsonProperty("description")
    private String description;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("displayNameOrNull")
    private Object displayNameOrNull;
    @JsonProperty("fullDisplayName")
    private String fullDisplayName;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("buildable")
    private Boolean buildable;
    @JsonProperty("builds")
    private List<BaseBuild> builds = null;
    @JsonProperty("color")
    private String color;
    @JsonProperty("firstBuild")
    private BaseBuild firstBuild;
    @JsonProperty("healthReport")
    private List<Object> healthReport = null;
    @JsonProperty("inQueue")
    private Boolean inQueue;
    @JsonProperty("keepDependencies")
    private Boolean keepDependencies;
    @JsonProperty("lastBuild")
    private BaseBuild lastBuild;
    @JsonProperty("lastCompletedBuild")
    private BaseBuild lastCompletedBuild;
    @JsonProperty("lastFailedBuild")
    private BaseBuild lastFailedBuild;
    @JsonProperty("lastStableBuild")
    private BaseBuild lastStableBuild;
    @JsonProperty("lastSuccessfulBuild")
    private BaseBuild lastSuccessfulBuild;
    @JsonProperty("lastUnstableBuild")
    private Object lastUnstableBuild;
    @JsonProperty("lastUnsuccessfulBuild")
    private BaseBuild lastUnsuccessfulBuild;
    @JsonProperty("nextBuildNumber")
    private Integer nextBuildNumber;
    @JsonProperty("property")
    private List<Object> property = null;
    @JsonProperty("queueItem")
    private Object queueItem;
    @JsonProperty("concurrentBuild")
    private Boolean concurrentBuild;
    @JsonProperty("resumeBlocked")
    private Boolean resumeBlocked;
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
