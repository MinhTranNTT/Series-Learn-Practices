package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
}
