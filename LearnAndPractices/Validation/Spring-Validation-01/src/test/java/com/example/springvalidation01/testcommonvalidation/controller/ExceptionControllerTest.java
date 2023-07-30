package com.example.springvalidation01.testcommonvalidation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void throwIllegalArgumentException() throws Exception {
        mockMvc.perform(get("/api/illegalArgumentException"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Error Parameter"));
    }

    @Test
    void throwResourceNotFoundException() {
    }
}