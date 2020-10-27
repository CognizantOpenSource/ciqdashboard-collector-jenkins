package com.cognizant.dashboard.collectors.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
@EnableAutoConfiguration
@EnableEncryptableProperties
public class JenkinsCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JenkinsCollectorApplication.class, args);

	}

}
