package com.dragon.redis.service;

import com.dragon.redis.entity.User;
import com.dragon.redis.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = { "user" })   // append value to form key "user::1"
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired private UserMapper userMapper;

    @Cacheable(key="#id")
    public User findUserById(Integer id){
        return this.userMapper.selectByPrimaryKey(id);
    }

    @CachePut(key = "#obj.id")
    public User updateUser(User obj){
        this.userMapper.updateByPrimaryKeySelective(obj);
        return this.userMapper.selectByPrimaryKey(obj.getId());
    }

    @CacheEvict(key = "#id")
    public void deleteUser(Integer id){
        User user=new User();
        user.setId(id);
        user.setDeleted((byte)1);
        this.userMapper.updateByPrimaryKeySelective(user);
    }
}
