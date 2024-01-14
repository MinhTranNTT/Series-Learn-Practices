package com.example.hibernatevalid.domain;

import com.example.hibernatevalid.validation.Even;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Father {
    @Null(message = "Primary key cannot have a value")
    private Integer id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be empty")
    @Even
    private Integer age;
    // {
    //     "name": "test",
    //         "age": 11,
    //         "friends": ["A", "B"]
    // }

    @NotNull(message = "ListFather not null")
    @Even
    private List<String> friends;
    // {
    //     "name": "test",
    //         "age": 10,
    //         "friends": ["A", "B", "C"]
    // }

}
