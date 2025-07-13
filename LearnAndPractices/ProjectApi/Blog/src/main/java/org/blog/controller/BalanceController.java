package org.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalance() {
        String txt = "hallo".toUpperCase();
        return "Balance Controller";
    }
}
