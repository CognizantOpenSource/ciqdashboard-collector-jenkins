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

package com.cognizant.dashboard.collectors.jenkins.service;

import com.cognizant.dashboard.collectors.jenkins.beans.core.JenkinsBuild;
import com.cognizant.dashboard.collectors.jenkins.db.repo.JenkinsBuildRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * JenkinsBuildService
 * @author Cognizant
 */

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

    public List<JenkinsBuild> getAllJobBuilds() {
        return repository.findAll();
    }

    public void delete(JenkinsBuild build) {
        repository.delete(build);
    }

    public List<JenkinsBuild> findByJobName(String jobName) {
        return repository.findByJobName(jobName);
    }

    public List<JenkinsBuild> getAll() {
        return repository.findAll();
    }

    public List<JenkinsBuild> deleteByJobName(String jobName)  {
        return repository.deleteByJobName(jobName);
    }

    public Optional<JenkinsBuild> findByUrl(String url) {
        return repository.findByUrl(url);
    }
}
