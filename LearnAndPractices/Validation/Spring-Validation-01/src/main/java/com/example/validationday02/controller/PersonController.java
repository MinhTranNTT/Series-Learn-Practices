package com.example.validationday02.controller;

import com.example.validationday02.entity.PersonRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("api/persons")
@Validated
public class PersonController {

    @PostMapping
    public ResponseEntity<PersonRequest> save(@RequestBody @Valid PersonRequest personRequest) {
        return ResponseEntity.ok().body(personRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integer> getPersonById(@Valid @PathVariable("id") @Max(value = 5, message = "Exceeded the range of id") Integer id) {
        return ResponseEntity.ok().body(id);
    }

    @PutMapping
    public ResponseEntity<String> getPersonByName(@Valid @RequestParam("name") @Size(max = 6, message = "Exceeded the range of name") String name) {
        return ResponseEntity.ok().body(name);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
