package com.example.testcommonvalidation.controller;

import com.example.springvalidation01.SpringValidation01Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringValidation01Application.class)  // suggests this test is part of Spring Boot, because it is outside @SpringBootApplication
@AutoConfigureMockMvc
class ExceptionControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    void throwIllegalArgumentException() throws Exception {
        mockMvc.perform(get("/api/illegalArgumentException"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Error Parameter"));
    }

    @Test
    void throwResourceNotFoundException() throws Exception {
        mockMvc.perform(get("/api/resourceNotFoundException"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message").value("Sorry, the resource not found!"));
    }
}