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
import java.util.*;
import java.util.stream.*;

/**
 * JenkinsBuildComponent - Jenkins build component
 * @author Cognizant
 */

@Component
@Slf4j
public class JenkinsBuildComponent {
    @Autowired
    JenkinsCommonUtility commonUtility;
    @Autowired
    JenkinsClient client;
    @Autowired
    JenkinsBuildService jenkinsBuildService;
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

        removeDeletedBuildsFromDB(jobs);

        jobs.forEach(baseJob -> {
            JenkinsJob jenkinsJobDetails = client.getJenkinsJobDetails(baseJob.getJobName(), requestHeader);
            getJobBuilds(jenkinsJobDetails);
        });
    }

    public JenkinsDetails getJobs() {
        return client.getJenkinsDetails(requestHeader);
    }

    public void getJobBuilds(JenkinsJob job){

//        updatePendingBuilds(job.getName());
        List<BaseBuild> builds = job.getBuilds();

        removeDeletedBuildsFromDB(job, builds);

        List<Integer> buildNumbers = builds.stream().map(BaseBuild::getNumber).collect(Collectors.toList());
        Collections.reverse(buildNumbers);
        Integer maxBuildNumber = jenkinsBuildService.getMaxBuildNumber(job.getName());
//        if (job.getLastBuild() != null && job.getLastBuild().getNumber() == maxBuildNumber) {
//            return;
//        }

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

    /*
     * To remove deleted builds from DB
     */
    private void removeDeletedBuildsFromDB(JenkinsJob job, List<BaseBuild> builds) {

        List<JenkinsBuild> jenkinsBuildsInDb = jenkinsBuildService.findByJobName(job.getName());

        jenkinsBuildsInDb.forEach(buildInDb ->{
            Stream<BaseBuild> filteredBuilds = builds.stream().filter(buildInRes -> buildInRes.getUrl().equals(buildInDb.getUrl()));
            if(filteredBuilds.findAny().isEmpty())  {
                jenkinsBuildService.delete(buildInDb);
            }
        });
    }

    /*
     * To remove builds in DB for renamed and deleted jobs
     */
    private void removeDeletedBuildsFromDB(List<BaseJob> jobs) {

        List<String> jobNamesInResponse = jobs.stream().map(BaseJob::getJobName).collect(Collectors.toList());
        List<String> jobNamesInDB = jenkinsBuildService.getAll().stream().map(JenkinsBuild::getJobName).collect(Collectors.toList());

        jobNamesInDB.removeAll(jobNamesInResponse);
        jobNamesInDB.forEach(deletedJob -> jenkinsBuildService.deleteByJobName(deletedJob));

    }

//    public void updatePendingBuilds(String jobName) {
//        List<JenkinsBuild> builds = customJenkinsBuildRepository.getNullResultBuilds(jobName);
//        builds.forEach(build -> {
//            JenkinsBuild buildDetails = getBuildDetails(build.getJobName(), build.getJobFullName(), build.getUrl(), build.getJobDescription(), build.getNumber());
//            buildDetails.setBuildUniqueRef(build.getBuildUniqueRef());
//            saveJobBuild(buildDetails);
//        });
//    }

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

    public void saveJobBuild(JenkinsBuild buildDetails) {

        Optional<JenkinsBuild> optional= jenkinsBuildService.findByUrl(buildDetails.getUrl());
        optional.ifPresent(build -> buildDetails.setBuildUniqueRef(build.getBuildUniqueRef()));

        jenkinsBuildService.save(buildDetails);
    }


}