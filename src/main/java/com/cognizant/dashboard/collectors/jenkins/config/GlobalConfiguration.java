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

package com.cognizant.dashboard.collectors.jenkins.config;

import com.cognizant.dashboard.collectors.jenkins.client.JenkinsClient;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
/**
 * GlobalConfiguration - Global feign client configuration class
 * @author Cognizant
 */

@Configuration
@Slf4j
public class GlobalConfiguration {

//    private static final String AUTHORIZATION = "Authorization";
    @Value("${jenkins.url}")
    private String jenkinsUrl;

    @Bean
    public JenkinsClient jenkinsClient() {

        return Feign.builder()
                .contract(new SpringMvcContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .options(new Request.Options(20000, TimeUnit.MILLISECONDS, 20000, TimeUnit.MILLISECONDS, true))
                .logger(new Slf4jLogger(JenkinsClient.class))
                .logLevel(Logger.Level.FULL)
                .target(JenkinsClient.class,jenkinsUrl);
    }

}


