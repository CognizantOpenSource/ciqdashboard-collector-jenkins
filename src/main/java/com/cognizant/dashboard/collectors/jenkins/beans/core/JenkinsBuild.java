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
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * JenkinsBuild - Refers to jenkinsBuild collection in mongodb
 * @author Cognizant
 */

@Document(collection = "#{T(com.cognizant.dashboard.collectors.jenkins.component.JenkinsCommonUtility).getCollectionName()}")
@Data
@CompoundIndex(name = "jobName_buildId_index", def = "{'jobName' : 1, 'number': 1}", unique = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "jobFullName",
        "jobDescription",
        "jobName",
        "jobURL",
        "building",
        "description",
        "displayName",
        "duration",
        "estimatedDuration",
        "executor",
        "fullDisplayName",
        "buildId",
        "keepLog",
        "number",
        "queueId",
        "result",
        "timestamp",
        "buildStartTime",
        "buildEndTime",
        "url",
        "changeSets",
        "culprits",
        "nextBuild",
        "previousBuild"
})
public class JenkinsBuild {
    @Id
    private String buildUniqueRef;

    @JsonProperty("jobFullName")
    private String jobFullName;
    @JsonProperty("jobDescription")
    private String jobDescription;
    @JsonProperty("jobName")
    private String jobName;
    @JsonProperty("jobURL")
    private String jobURL;

    @JsonProperty("building")
    private Boolean building;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("estimatedDuration")
    private Integer estimatedDuration;
    @JsonProperty("executor")
    private Object executor;
    @JsonProperty("fullDisplayName")
    private String fullDisplayName;
    @JsonProperty("id")
    private String buildId;
    @JsonProperty("keepLog")
    private Boolean keepLog;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("queueId")
    private Integer queueId;
    @JsonProperty("result")
    private String result;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("buildStartTime")
    private Date buildStartTime;
    @JsonProperty("buildEndTime")
    private Date buildEndTime;
    @JsonProperty("url")
    private String url;
    @JsonProperty("changeSets")
    private List<Object> changeSets = null;
    @JsonProperty("culprits")
    private List<Object> culprits = null;
    @JsonProperty("nextBuild")
    private BaseBuild _nextBuild;
    @JsonProperty("previousBuild")
    private BaseBuild _previousBuild;

    @JsonIgnore
    private Map<String, Object> _additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> get_additionalProperties() {
        return this._additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this._additionalProperties.put(name, value);
    }

}
