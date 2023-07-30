package com.example.validationday02.controller;

import com.example.springvalidation01.SpringValidation01Application;
import com.example.validationday02.entity.PersonRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringValidation01Application.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    public void save() throws Exception {
        PersonRequest personRequest = PersonRequest.builder().sex("Man22").classId("12345678").build();
        mockMvc.perform(post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(MockMvcResultMatchers.jsonPath("sex").value("sex the value is not in the optional range"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("name not blank"));
    }

    @Test
    void getPersonById() throws Exception {
        mockMvc.perform(get("/api/persons/6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("getPersonById.id: Exceeded the range of id"));
    }

    @Test
    void getPersonByName() throws Exception {
        mockMvc.perform(put("/api/persons")
                        .param("name", "minhminhminh")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("getPersonByName.name: Exceeded the range of name"));
    }


    @Test
    public void testSavePersonRegionCustomAnnotationValidation() throws Exception {
        PersonRequest personRequest = PersonRequest.builder()
                .region("VNI").build();
        mockMvc.perform(post("/api/persons")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(MockMvcResultMatchers.jsonPath("region").value("Region value is not in optional range"));
    }

    @Test
    public void testValidationRequest() throws Exception {
        PersonRequest personRequest = PersonRequest.builder()
                .sex("Man22").classId("1234567890").region("Bali").build();
        mockMvc.perform(post("/api/persons")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(MockMvcResultMatchers.jsonPath("region").value("Region value is not in optional range"))
                .andExpect(MockMvcResultMatchers.jsonPath("sex").value("sex the value is not in the optional range"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("name not blank"));
    }

    @Test
    public void testValidationPhoneNumber() throws Exception {
        PersonRequest personRequest = PersonRequest.builder()
                .phoneNumber("9841234567").build();
        mockMvc.perform(post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(MockMvcResultMatchers.jsonPath("phoneNumber").value("phone number is not in the correct format"));
    }
}