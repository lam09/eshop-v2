package com.lam.eshopv2.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by a.lam.tuan on 16. 8. 2018.
 */
@Controller
public class AppController {
    @ResponseBody
    @RequestMapping(path = "/admin/shutdown")
    public String callActuatorShutdown() {

        // Actuator Shutdown Endpoint:
        String url = "https://localhost:15000/actuator/shutdown";

        // Http Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<String> requestBody = new HttpEntity<>("", headers);

        // Send request with POST method.
        String e = restTemplate.postForObject(url, requestBody, String.class);

        return "Result: " + e;
    }



}
