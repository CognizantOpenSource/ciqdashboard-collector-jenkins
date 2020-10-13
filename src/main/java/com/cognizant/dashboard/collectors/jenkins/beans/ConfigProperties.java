package com.cognizant.dashboard.collectors.jenkins.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jenkins")
@Data
public class ConfigProperties {
    private String url;
    private String username;
    private String token;
}
