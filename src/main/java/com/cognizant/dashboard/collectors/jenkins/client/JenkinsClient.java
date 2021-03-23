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

package com.cognizant.dashboard.collectors.jenkins.client;

import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsBuild;
import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsDetails;
import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsJob;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface JenkinsClient {

    @GetMapping("/api/json")
    JenkinsDetails getJenkinsDetails(@RequestHeader HttpHeaders requestHeader);

    @GetMapping("job/{jobName}/api/json")
    JenkinsJob getJenkinsJobDetails(@RequestParam("jobName") String jobName,
                                    @RequestHeader HttpHeaders requestHeader);

    @GetMapping("/job/{jobName}/{buildId}/api/json")
    JenkinsBuild getJenkinsBuildDetails(@RequestParam("jobName") String jobName,
                                        @RequestParam("buildId") int buildId,
                                        @RequestHeader HttpHeaders requestHeader);

}
