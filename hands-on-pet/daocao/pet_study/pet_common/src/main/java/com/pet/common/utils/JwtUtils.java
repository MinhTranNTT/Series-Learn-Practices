package com.pet.common.utils;

import cn.hutool.core.util.StrUtil;
import com.pet.common.constants.CacheConstants;
import com.pet.common.domain.vo.LoginUserVO;
import com.pet.common.utils.redis.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/*
 * 通过jwt生成token和解析token，刷新token
 * */
@Component
@Slf4j
@AllArgsConstructor
public class JwtUtils {
    private final String secret = "wqewqdidsaiodjaiodoand";
    private final RedisCacheUtil redisCacheUtil;
    /*
     * 创建token，会将用户数据存放在reids中
     * 可以方便的实现单点登录，实现下线踢人，查看在线用户等等功能
     * 可以使用UUID当作redis的key
     * */
    public String createToken(LoginUserVO loginUserVO) {
        // 创建一个map
        String token = UUID.randomUUID().toString().replace("-","");
        // 将UUID存储到登录用户中，可以在后台系统中根据token获取redis中的数据
        loginUserVO.setToken(token);
        Long currentTimeMillis = System.currentTimeMillis();
        loginUserVO.setLogTime(currentTimeMillis);
        Map<String, Object> claims = new HashMap<>();
        claims.put("token",token);
        // 将值存入redis数据库中，
        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY + token,loginUserVO,30, TimeUnit.MINUTES);
        log.info("创建token");
        // refreshToken(loginUserVO);
        // compact 将上述内容合并
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    /*
     * 解析token
     * token：JWT字符串 ****.****.****
     * */
    public Claims parseToken(String token){
        // getBody是获取JWT的声明部分
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Object getLoginUser(HttpServletRequest request) {
        // 获取Jwt加密过的token
        String token = request.getHeader("Daocao-Authorization");
        if(StrUtil.isNotEmpty(token)){
            log.info("token============>"+token);
            // 解析token，
            Claims claims = parseToken(token);
            String parseToken = (String) claims.get("token");
            System.out.println("parseToken=========>"+parseToken);
            // 从redis中获取数据
            LoginUserVO loginUserVO = redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + parseToken);
            // 刷新token
            long currentTimeMillis = System.currentTimeMillis();
            long time = loginUserVO.getLogTime();
            long minute = currentTimeMillis/1000/60 - time/1000/60;
            log.info("The current time difference is:"+minute);
            if(minute>=20){
                refreshToken(loginUserVO);
            }
            return loginUserVO;

        }
        return null;
    }

    private void refreshToken(LoginUserVO loginUserVO){
        // 将值存入redis数据库中，
        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY+loginUserVO.getToken(),loginUserVO,30, TimeUnit.MINUTES);
    }

}
