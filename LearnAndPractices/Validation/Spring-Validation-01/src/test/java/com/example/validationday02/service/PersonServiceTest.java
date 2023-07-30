package com.example.validationday02.service;

import com.example.springvalidation01.SpringValidation01Application;
import com.example.validationday02.entity.Person;
import com.example.validationday02.entity.PersonRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.*;
import java.util.Set;

@SpringBootTest(classes = SpringValidation01Application.class)
@RunWith(SpringRunner.class)
class PersonServiceTest {

    @Autowired private PersonService personService;
    @Autowired private Validator validator;

    @Test
    public void validatePersonRequest() {
        try {
            PersonRequest personRequest = PersonRequest.builder()
                    .sex("Man22").classId("12345678").build();
            personService.validatePersonRequest(personRequest);
        } catch (ConstraintViolationException exception) {
            exception.getConstraintViolations().forEach(constraintViolation ->
                    System.out.println(constraintViolation.getMessage())
            );
        }
    }

    @Test
    public void checkPersonManually() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        PersonRequest personRequest = PersonRequest.builder()
                .sex("Man22").classId("12345678").build();
        Set<ConstraintViolation<PersonRequest>> violations = validator.validate(personRequest);
        violations.forEach(constraintViolation ->
                System.out.println(constraintViolation.getMessage())
        );
    }

    @Test
    public void checkPersonManuallyByValidator() {
        PersonRequest personRequest = PersonRequest.builder()
                .sex("Man22").classId("12345678").build();
        Set<ConstraintViolation<PersonRequest>> violations = validator.validate(personRequest);
        violations.forEach(constraintViolation ->
                System.out.println(constraintViolation.getMessage())
        );
    }

    @Test
    public void testPersonRegionByCustomAnnotationValidation() {
        PersonRequest personRequest = PersonRequest.builder()
                .sex("Man").classId("12345678").region("VNI").build();
        Set<ConstraintViolation<PersonRequest>> violations = validator.validate(personRequest);
        violations.forEach(constraintViolation ->
                System.out.println(constraintViolation.getMessage())
        );
    }

    @Test
    public void shouldCheckPersonWithGroups() {
        Person person = new Person();
        person.setGroup(null);
        personService.validatePersonGroupForAdd(person);
    }

    @Test
    public void shouldCheckPersonWithGroups2() {
        Person person = new Person();
        personService.validatePersonGroupForDelete(person);
    }

}