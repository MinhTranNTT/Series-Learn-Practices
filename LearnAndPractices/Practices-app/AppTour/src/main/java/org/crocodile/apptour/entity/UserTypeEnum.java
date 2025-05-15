package org.crocodile.apptour.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    ADMIN("I'm a Boss"),
    GUEST("I'm a Guess"),
    PREMIUM("I'm a PREMIUM");

    private final String description;

}
