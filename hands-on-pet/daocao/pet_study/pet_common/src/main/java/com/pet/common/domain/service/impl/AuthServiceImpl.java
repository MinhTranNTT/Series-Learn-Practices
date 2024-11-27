package com.pet.common.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.pet.common.constants.HttpStatus;
import com.pet.common.domain.dto.LoginDto;
import com.pet.common.domain.service.IAuthService;
import com.pet.common.domain.vo.LoginUserVO;
import com.pet.common.exception.ServiceException;
import com.pet.common.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @Override
    public String login(LoginDto loginDto) {
        // 用于封装用户提供的账户信息和密码信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getAccount(), loginDto.getPassword());
        // 对用户的身份执行身份验证操作
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 获取用户信息
        LoginUserVO loginUserVO = (LoginUserVO) authenticate.getPrincipal();
        // 根据loginUserV0创建token
        if(ObjectUtil.isNull(loginUserVO)){
            throw new ServiceException(HttpStatus.UNAUTHORIZED,"认证失败!");
        }
        //创建token
        String token = jwtUtils.createToken(loginUserVO);
        log.info("token====>"+token);
        return token;
    }
}
