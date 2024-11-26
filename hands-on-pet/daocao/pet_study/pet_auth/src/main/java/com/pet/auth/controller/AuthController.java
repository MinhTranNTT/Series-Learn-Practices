package com.pet.auth.controller;

import com.pet.auth.domain.dto.LoginDto;
import com.pet.common.response.PetResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Slf4j
@AllArgsConstructor
public class AuthController {
    // private final IAuthService authService;

    /*
     * 用户系统登录
     * */
    @PostMapping("sys")
    public PetResult sysLogin(@RequestBody LoginDto loginDto){
        log.info("LoginDto=======>{}",loginDto);
        return PetResult.success();
        // String token = authService.login(loginDto);
        // return PetResult.success().put("token",token);
    }

}
