package com.example.validationday02.entity;

import com.example.validationday02.service.AddPersonGroup;
import com.example.validationday02.service.DeletePersonGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Person {
    // When the verification group is DeletePersonGroup, the group field cannot be empty
    @NotNull(groups = DeletePersonGroup.class)
    // When the verification group is AddPersonGroup, the group field cannot be empty
    @NotNull(groups = AddPersonGroup.class)
    private String group;
}
