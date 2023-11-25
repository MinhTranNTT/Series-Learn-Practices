package com.example.goodcontroller.controller;

import com.example.goodcontroller.dto.TestDTO;
import com.example.goodcontroller.service.TestService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "prettyTestController")
@RequestMapping("/pretty")
public class TestController02 {
    private TestService testService;

    @GetMapping("/{num}")
    public Integer detail(@PathVariable("num") @Min(1) @Max(20) Integer num) {
        return num * num;
    }

    @GetMapping("/getByEmail")
    public TestDTO getByAccount(@RequestParam @NotBlank @Email String email) {
        TestDTO testDTO = new TestDTO();
        testDTO.setEmail(email);
        return testDTO;
    }

    @Autowired
    public void setTestService(TestService prettyTestService) {
        this.testService = prettyTestService;
    }
}
