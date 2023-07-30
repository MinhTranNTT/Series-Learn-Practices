package com.example.validationday02.entity;

import com.example.validationday02.customvalidation.PhoneNumber;
import com.example.validationday02.customvalidation.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    @NotNull(message = "classId not blank")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name not blank")
    private String name;

    @Pattern(regexp = "(^Man$|^Woman$|^UGM$)", message = "sex the value is not in the optional range")
    @NotNull(message = "sex not blank")
    private String sex;

    @Region
    private String region;

    @PhoneNumber(message = "phone number is not in the correct format")
    @NotNull(message = "phone not blank")
    private String phoneNumber;
}
