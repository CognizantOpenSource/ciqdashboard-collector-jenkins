package com.cognizant.dashboard.collectors.jenkins.db.impl;

import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomJenkinsBuildRepository {

    @Autowired
    MongoTemplate template;
	String nullVar= null;

    public List<JenkinsBuild> getNullResultBuilds(String jobName){
        Query query = new Query();
        query.addCriteria(Criteria.where("jobName").is(jobName));
        query.addCriteria(Criteria.where("result").is(nullVar));
        return template.find(query, JenkinsBuild.class);
    }

}
