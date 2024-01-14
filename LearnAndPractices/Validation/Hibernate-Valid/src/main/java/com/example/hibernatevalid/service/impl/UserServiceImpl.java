package com.example.hibernatevalid.service.impl;

import com.example.hibernatevalid.domain.User;
import com.example.hibernatevalid.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserServiceImpl implements UserService {
    @Override
    public void add(User user) {
        //code business
        System.out.println("Data added successfully");
    }

    @Override
    public User getById(Integer id) {
        return null;
    }
}
