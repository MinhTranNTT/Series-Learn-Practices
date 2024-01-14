package com.example.hibernatevalid.service;

import com.example.hibernatevalid.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UserService {
    void add(@Valid User user);

    //Data verification can also be verified like this
    @NotNull User getById(@NotNull Integer id);
}
