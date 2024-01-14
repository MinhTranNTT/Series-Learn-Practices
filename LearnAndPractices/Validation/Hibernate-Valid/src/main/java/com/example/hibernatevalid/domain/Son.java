package com.example.hibernatevalid.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Son {
    @Null(message = "Primary key cannot have a value")
    private Integer id;

    @NotBlank(message = "The name cannot be blank")
    private String name;

    @NotNull(message = "Age cannot be null")
    private Integer age;
}
