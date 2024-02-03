package com.cj.concurrencystep.exchange.controller;

import com.cj.concurrencystep.exchange.domain.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    // @GetMapping(produces = "application/vnd.api.v1+json")
    // public Hello hello2() {
    //     return new Hello("zażółć gęślą jaźń");
    // }

    // produces = "application/vnd.api.v1+json"
    // @GetMapping()
    // public ResponseEntity<Map<String, String>> hello() {
    //     Map<String, String> response = new HashMap<>();
    //     response.put("message", "zażółć gęślą jaźń");
    //     return ResponseEntity.ok(response);
    // }

    @GetMapping()
    public List<Hello> hello() {
        List<String> images = List.of("img1.png","img2.png");

        Hello hello1 = new Hello();
        hello1.setName("TEO");
        hello1.setOrderName("Order1");
        hello1.setCustomerName("Cus1");
        hello1.setAge(12);
        hello1.setStock(1);
        hello1.setImageList(images);

        Hello hello2 = new Hello();
        hello2.setName("TY");
        hello2.setAge(12);
        hello2.setCustomerName("Cus2");
        hello2.setOrderName("Order2");
        hello2.setStock(1);
        hello2.setImageList(images);

        return Arrays.asList(hello1,hello2);
    }

    @GetMapping("/improve")
    public ResponseEntity<?> hello2() {
        List<String> images = List.of("img1.png","img2.png");

        Hello hello1 = new Hello();
        hello1.setName("TEO");
        hello1.setOrderName("Order1");
        hello1.setImageList(images);

        Hello hello2 = new Hello();
        hello2.setName("TY");
        hello2.setAge(12);
        hello2.setStock(0);
        hello2.setImageList(images);

        return ResponseEntity.ok(Arrays.asList(hello1,hello2));
    }

}
