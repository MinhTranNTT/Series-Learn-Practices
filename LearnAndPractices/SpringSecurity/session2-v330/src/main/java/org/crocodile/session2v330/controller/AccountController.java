package org.crocodile.session2v330.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "myAccount";
    }
}
