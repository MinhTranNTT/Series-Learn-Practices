package org.blog.controller;

import lombok.RequiredArgsConstructor;
import org.blog.entity.Customer;
import org.blog.entity.OwnerCheck;
import org.blog.mapper.CustomerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final CustomerMapper customerMapper;

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "Detail Account";
    }

    @GetMapping("/myAccount/{id}")
    @OwnerCheck(pathId = "id")
    public ResponseEntity<?> getAccountDetails(@PathVariable("id") Long id) {
        Customer customerById = customerMapper.getCustomerById(id);
        Optional.ofNullable(customerById)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        return ResponseEntity.ok().body(customerById);
    }

}
