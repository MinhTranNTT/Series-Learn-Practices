package com.example.mybatisday02;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MybatisDay02ApplicationTests {
    @Autowired private UserMapper userMapper;

    @Test
    public void testSelectUser() {
        List<User> users = this.userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
