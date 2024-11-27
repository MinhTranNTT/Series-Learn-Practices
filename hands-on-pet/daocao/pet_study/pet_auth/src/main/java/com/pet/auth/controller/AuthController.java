package com.pet.auth.controller;

import com.pet.common.domain.dto.LoginDto;
import com.pet.common.domain.service.IAuthService;
import com.pet.common.response.PetResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Slf4j
@AllArgsConstructor
public class AuthController {
    private final IAuthService authService;

    /*
     * 用户系统登录
     * */
    @PostMapping("sys")
    public PetResult sysLogin(@RequestBody LoginDto loginDto){
        log.info("LoginDto=======>{}",loginDto);
        String token = authService.login(loginDto);
        return PetResult.success().put("token",token);
    }

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public void testAAA() {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

}
