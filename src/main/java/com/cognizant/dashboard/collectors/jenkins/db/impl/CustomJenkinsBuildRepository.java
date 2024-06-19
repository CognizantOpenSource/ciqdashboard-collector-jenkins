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

package com.cognizant.dashboard.collectors.jenkins.db.impl;

import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * CustomJenkinsBuildRepository
 * @author Cognizant
 */

@Repository
public class CustomJenkinsBuildRepository {

    @Autowired
    MongoTemplate template;

//    public List<JenkinsBuild> getNullResultBuilds(String jobName)   {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("jobName").is(jobName));
//        query.addCriteria(Criteria.where("result").is(null));
//        return template.find(query, JenkinsBuild.class);
//    }

}
