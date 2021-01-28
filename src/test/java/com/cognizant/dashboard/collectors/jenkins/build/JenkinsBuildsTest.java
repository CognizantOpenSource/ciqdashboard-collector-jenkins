package com.cognizant.dashboard.collectors.jenkins.build;

import com.cognizant.dashboard.collectors.jenkins.component.JenkinsBuildComponent;
import com.cognizant.dashboard.collectors.jenkins.service.JenkinsBuildService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JenkinsBuildsTest {
    @Autowired
    JenkinsBuildComponent component;
    @Autowired
    JenkinsBuildService jenkinsBuildService;

    @Test
    void updatePendingTest(){
        Assertions.assertNotNull(component);
        component.updatePendingBuilds("Test");
    }

    @Test
    void getMaxBuildNumber() {
        Assertions.assertNotNull(jenkinsBuildService);
        Integer maxBuildNumber = jenkinsBuildService.getMaxBuildNumber("Test1");
//        System.out.println(maxBuildNumber);
        Assertions.assertNotNull(maxBuildNumber);
    }
}
