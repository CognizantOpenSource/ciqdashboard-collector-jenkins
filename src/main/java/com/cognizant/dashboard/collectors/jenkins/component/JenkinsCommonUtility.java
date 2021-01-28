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
