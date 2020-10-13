package com.cognizant.dashboard.collectors.jenkins.service;

import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsBuild;
import com.cognizant.dashboard.collectors.jenkins.db.repo.JenkinsBuildRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JenkinsBuildService {

    @Autowired
    JenkinsBuildRepository repository;

    public JenkinsBuild save(JenkinsBuild buildDetails) {
        return repository.save(buildDetails);
    }

    public Integer getMaxBuildNumber(String jobName){
        JenkinsBuild first = repository.findFirstByJobNameOrderByNumberDesc(jobName);
        return first == null ? null : first.getNumber();
    }

}
