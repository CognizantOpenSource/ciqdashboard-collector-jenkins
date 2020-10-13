package com.cognizant.dashboard.collectors.jenkins.component;

import com.cognizant.dashboard.collectors.jenkins.beans.ConfigProperties;
import com.cognizant.dashboard.collectors.jenkins.config.CustomBasicAuthentication;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class JenkinsCommonUtility {
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
}
