package com.cognizant.dashboard.collectors.jenkins.db.repo;

import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsBuild;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JenkinsBuildRepository extends MongoRepository<JenkinsBuild,String> {
    JenkinsBuild findFirstByJobNameOrderByNumberDesc(String jobName);
}
