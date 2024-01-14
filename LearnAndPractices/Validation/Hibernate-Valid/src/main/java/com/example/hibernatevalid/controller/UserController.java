package com.example.hibernatevalid.controller;

import com.example.hibernatevalid.domain.User;
import com.example.hibernatevalid.result.ResultVo;
import com.example.hibernatevalid.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated // Enable parameter verification function for methods in this class
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ResultVo user(@RequestBody @Valid User user){
        //处理业务逻辑
        return ResultVo.success();
    }

    /*
    {
    // "father": {},
    "sons": [
        {},{}
    ]
    }
    * */

    @RequestMapping("/userService")
    public ResultVo userService(@RequestBody User user){
        //Handle business logic
        userService.add(user);
        return ResultVo.success();
    }

    /*{
        "id": 1,
            "birthday": "2020-12-12",
            "name": "test",
            "age": 10,
            "email": "minh@gmail.com"
    }*/

    @RequestMapping("/addUser")
    public ResultVo add(@RequestBody @Validated({User.Add.class, Default.class}) User user) {
        //增加用户时，数据校验就采用Add验证组和默认验证组
        //处理业务逻辑
        return ResultVo.success();
    }

    @RequestMapping("/updateUser")
    public ResultVo update(@RequestBody @Validated({User.Update.class, Default.class}) User user) {
        //更新用户时，数据校验就采用Update验证组和默认验证组
        //处理业务逻辑
        return ResultVo.success();
    }

}
