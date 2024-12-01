package com.pet.common.utils.security;

import cn.hutool.core.util.ObjectUtil;
import com.pet.common.constants.HttpStatus;
import com.pet.common.domain.vo.LoginUserVO;
import com.pet.common.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PetSecurityUtil {
    // 获取Authentication
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    // 获取用户信息
    public static LoginUserVO getLoginUser(){
        return (LoginUserVO) getAuthentication().getPrincipal();
    }
    // 获取userId
    public static Long getUserId(){
        Long userId = getLoginUser().getId();
        if(ObjectUtil.isNull(userId)){
            throw new ServiceException(HttpStatus.FORBIDDEN,"");
        }
        return userId;
    }
    public static String getUserName(){
        String username = getLoginUser().getUsername();
        if(ObjectUtil.isNull(username)){
            throw  new ServiceException(HttpStatus.FORBIDDEN,"");
        }
        return username;
    }
}
