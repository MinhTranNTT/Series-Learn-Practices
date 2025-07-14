package org.blog.democonsumerapi.controller;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/client")
@Slf4j
public class ApiClientController {

    private RestTemplate restTemplate = new RestTemplate();
    //
    // @Value("${api.server.url}")
    // private String apiServerUrl;
    //
    // @GetMapping("/call1")
    // public String callSecureApi() {
    //     try {
    //         log.info("RUN");
    //         String response = restTemplate.getForObject(apiServerUrl, String.class);
    //         return "API Response: " + response;
    //     } catch (Exception e) {
    //         return "Error calling API: " + e.getMessage();
    //     }
    // }

    @GetMapping("/getHello")
    public void callGetHello() {
        log.info("Hello");
    }

    private final String apiServerUrlV2 = "http://localhost:8080/getHello";
    private final RateLimiter rateLimiter = RateLimiter.create(2.0);

    @GetMapping("/call2")
    public ResponseEntity<?> callSecureApiV2() {
        if (rateLimiter.tryAcquire(200, TimeUnit.MILLISECONDS)) {
            try {
                log.info("RUN");
                String response = restTemplate.getForObject(apiServerUrlV2, String.class);
                return ResponseEntity.ok().body("API Response: " + response);
            } catch (Exception e) {
                // return "Error calling API: " + e.getMessage();
                return ResponseEntity.badRequest().body("API Response: " + e.getMessage());

            }
        } else {
            // return "Rate limit exceeded in Consumer";
            return ResponseEntity.badRequest().body("Rate limit exceeded in Consumer");
        }
    }


}
