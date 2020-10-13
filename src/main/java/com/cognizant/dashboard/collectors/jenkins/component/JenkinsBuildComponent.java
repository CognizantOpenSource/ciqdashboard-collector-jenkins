package com.cognizant.dashboard.collectors.jenkins.component;

import com.cognizant.dashboard.collectors.jenkins.beans.core.*;
import com.cognizant.dashboard.collectors.jenkins.client.JenkinsClient;
import com.cognizant.dashboard.collectors.jenkins.db.impl.CustomJenkinsBuildRepository;
import com.cognizant.dashboard.collectors.jenkins.service.JenkinsBuildService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JenkinsBuildComponent {
    @Autowired
    JenkinsClient client;
    @Autowired
    JenkinsBuildService jenkinsBuildService;
    @Autowired
    JenkinsCommonUtility commonUtility;
    @Autowired
    CustomJenkinsBuildRepository customJenkinsBuildRepository;

    private HttpHeaders requestHeader = new HttpHeaders();

    @PostConstruct
    public void postConstructMethod(){
        requestHeader = commonUtility.getHeaders();
    }

    public void getAndUpdateBuildDetails(){
        JenkinsDetails jenkins = getJobs();
        List<BaseJob> jobs = jenkins.getJobs();
        jobs.forEach(baseJob -> {
            JenkinsJob jenkinsJobDetails = client.getJenkinsJobDetails(baseJob.getJobName(), requestHeader);
            getJobBuilds(jenkinsJobDetails);
        });
    }

    public JenkinsDetails getJobs() {
        return client.getJenkinsDetails(requestHeader);
    }

    public void getJobBuilds(JenkinsJob job){
        updatePendingBuilds(job.getName());
        List<BaseBuild> builds = job.getBuilds();
        List<Integer> buildNumbers = builds.stream().map(BaseBuild::getNumber).collect(Collectors.toList());
        Collections.reverse(buildNumbers);
        Integer maxBuildNumber = jenkinsBuildService.getMaxBuildNumber(job.getName());
        if (job.getLastBuild() != null && job.getLastBuild().getNumber() == maxBuildNumber)
            return;
        if (maxBuildNumber != null){
            int index = buildNumbers.indexOf(maxBuildNumber);
            if (index >= 0 && index < buildNumbers.size()-1){
                buildNumbers = buildNumbers.subList(index+1, buildNumbers.size());
            }
        }
        buildNumbers.forEach(buildNumber -> {
            JenkinsBuild buildDetails = getBuildDetails(job.getName(), job.getFullName(), job.getUrl(), job.getDescription(), buildNumber);
            saveJobBuild(buildDetails);
        });
    }

    public void updatePendingBuilds(String jobName) {
        List<JenkinsBuild> builds = customJenkinsBuildRepository.getNullResultBuilds(jobName);
        builds.forEach(build -> {
            JenkinsBuild buildDetails = getBuildDetails(build.getJobName(), build.getJobFullName(), build.getUrl(), build.getJobDescription(), build.getNumber());
            buildDetails.setBuildUniqueRef(build.getBuildUniqueRef());
            saveJobBuild(buildDetails);
        });
    }

    private JenkinsBuild getBuildDetails(String jobName, String jobFullName, String jobURL, String jobDescription, int buildNumber){
        JenkinsBuild buildDetails = client.getJenkinsBuildDetails(jobName, buildNumber, requestHeader);
        buildDetails.setJobDescription(jobDescription);
        buildDetails.setJobFullName(jobFullName);
        buildDetails.setJobName(jobName);
        buildDetails.setJobURL(jobURL);
        buildDetails.setBuildStartTime(new Date(buildDetails.getTimestamp()));
        buildDetails.setBuildEndTime(new Date(buildDetails.getTimestamp()+buildDetails.getDuration()));
        return buildDetails;
    }

    public JenkinsBuild saveJobBuild(JenkinsBuild buildDetails){
        return jenkinsBuildService.save(buildDetails);
    }
}
