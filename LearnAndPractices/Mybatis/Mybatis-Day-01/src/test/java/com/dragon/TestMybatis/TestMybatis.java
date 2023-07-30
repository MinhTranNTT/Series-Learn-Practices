package com.dragon.TestMybatis;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.dragon.mapper.UserMapper;
import com.dragon.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    @Test
    public void testUserList() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testUserListVersion2() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //MybatisSqlSessionFactoryBuilder
        SqlSessionFactory sqlSessionFactory = new
                MybatisSqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //BaseMapper
        List<User> list = userMapper.selectList(null);
        for (User user : list) {
            System.out.println(user);
        }
    }

}
