package org.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blog.dto.CustomerDto;
import org.blog.entity.Customer;
import org.blog.mapper.CustomerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            String hashPwd = passwordEncoder.encode(customerDto.getPwd());
            customerDto.setPwd(hashPwd);
            Optional.ofNullable(customerMapper.getCustomerByEmail(customerDto.getEmail()))
                    .ifPresent(c -> { throw new RuntimeException("User existing !!!"); });
            int createdCustomer = customerMapper.createCustomer(customerDto);
            if (customerDto.getId() > 0) {
                log.info("Created success Customer with ID = {}",customerDto.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body("Created success Customer");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An exception occurred");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred: " + e.getMessage());
        }
    }

}
