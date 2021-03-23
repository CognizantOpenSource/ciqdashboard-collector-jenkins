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

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
/**
 * CustomBasicAuthentication - Basic authentication class
 * @author Cognizant
 */

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
