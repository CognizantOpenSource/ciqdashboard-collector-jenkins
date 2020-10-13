package com.cognizant.dashboard.collectors.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
public class JenkinsCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JenkinsCollectorApplication.class, args);

	}

}
