package com.example.validationday02.service;

import com.example.validationday02.entity.Person;
import com.example.validationday02.entity.PersonRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PersonService {

    public void validatePersonRequest(@Valid PersonRequest personRequest) {
        // do something
    }

    @Validated(AddPersonGroup.class)
    public void validatePersonGroupForAdd(@Valid Person person) {
        // do something
    }

    @Validated(DeletePersonGroup.class)
    public void validatePersonGroupForDelete(@Valid Person person) {
        // do something
    }

}
