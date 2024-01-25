package com.cj.concurrencystep.exchange.controller;

import com.cj.concurrencystep.exchange.domain.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    // @GetMapping(produces = "application/vnd.api.v1+json")
    public Hello hello2() {
        return new Hello("zażółć gęślą jaźń");
    }

    // produces = "application/vnd.api.v1+json"
    @GetMapping()
    public ResponseEntity<Map<String, String>> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "zażółć gęślą jaźń");
        return ResponseEntity.ok(response);
    }
}
