package com.dragon.redis.controller;

import com.dragon.redis.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Api interface")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired private UserService userService;


}
