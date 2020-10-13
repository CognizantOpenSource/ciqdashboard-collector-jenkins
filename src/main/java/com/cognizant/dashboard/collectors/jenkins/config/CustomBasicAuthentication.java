package com.cognizant.dashboard.collectors.jenkins.config;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Service
public class CustomBasicAuthentication {
    private static final String AUTHORIZATION ="Authorization";

    public Map<String, String> getBasicAuthentication(String username, String token) {
        return getBasicAuthentication(username, token, StandardCharsets.ISO_8859_1);
    }

    public Map<String, String> getBasicAuthentication(String username, String token, Charset charset){
        String credentialsString = username + ":" + token;
        return Collections.singletonMap(AUTHORIZATION, "Basic " + Base64Utils.encodeToString(credentialsString.getBytes(charset)));
    }
}
