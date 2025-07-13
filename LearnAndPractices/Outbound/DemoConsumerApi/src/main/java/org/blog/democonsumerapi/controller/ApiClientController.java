package org.blog.democonsumerapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
@Slf4j
public class ApiClientController {

    @Autowired private RestTemplate restTemplate;

    @Value("${api.server.url}")
    private String apiServerUrl;

    @GetMapping("/call")
    public String callSecureApi() {
        try {
            log.info("RUN");
            String response = restTemplate.getForObject(apiServerUrl, String.class);
            return "API Response: " + response;
        } catch (Exception e) {
            return "Error calling API: " + e.getMessage();
        }
    }

    @GetMapping("/getHello")
    public void callGetHello() {
        log.info("Hello");
    }

}
