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
