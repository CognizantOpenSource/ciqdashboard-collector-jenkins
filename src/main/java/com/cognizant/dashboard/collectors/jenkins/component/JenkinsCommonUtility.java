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

import com.cognizant.dashboard.collectors.jenkins.beans.ConfigProperties;
import com.cognizant.dashboard.collectors.jenkins.config.CustomBasicAuthentication;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import static com.cognizant.dashboard.collectors.jenkins.constant.Constant.SOURCE;
/**
 * JenkinsCommonUtility - Common utility functions
 * @author Cognizant
 */

@Component
public class JenkinsCommonUtility {
    static String collectionName;
    private final JacksonDecoder decoder = new JacksonDecoder();
    private final HttpHeaders headers = new HttpHeaders();
    @Autowired
    ConfigProperties properties;
    @Autowired
    CustomBasicAuthentication customBasicAuthentication;

    @PostConstruct
    private void postConstructMethod() {
        headers.setAll(customBasicAuthentication.getBasicAuthentication(properties.getUsername(), properties.getToken()));
    }

    HttpHeaders getHeaders() {
        return headers;
    }

    @Value("${spring.data.mongodb.collection}")
    public void setCollectionName(String collectionName) {
        this.collectionName = SOURCE+collectionName;
    }

    public static String getCollectionName(){
        return collectionName;
    }
}
