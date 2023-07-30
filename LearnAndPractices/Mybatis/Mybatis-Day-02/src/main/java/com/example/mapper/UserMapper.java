package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;

//@Mapper
//@Repository
public interface UserMapper extends BaseMapper<User> {
    User findById(Long id);
}
