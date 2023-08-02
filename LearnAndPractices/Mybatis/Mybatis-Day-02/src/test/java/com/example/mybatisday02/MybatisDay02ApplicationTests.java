package com.example.mybatisday02;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testInsertUser() {
        User user = User.builder()
                .userName("minhtran2").password("1234567").age(22).email("minhtran2@gmail.com").build();
        int result = this.userMapper.insert(user);
        System.out.println("Result: " + result + " - " + user.getId());
    }

    @Test
    public void testUpdateById() {
        User user = User.builder().id(6L).name("minhtran").userName("Minh Tran").build();
        this.userMapper.updateById(user);
    }

    @Test
    public void testUpdateByWrapper() {
        User user = User.builder().age(25).build();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 6);
        int update = this.userMapper.update(user, queryWrapper);
        System.out.println("Updated");
    }

    @Test
    public void testDeleteById() {
        int result = this.userMapper.deleteById(6L);
        System.out.println(result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 25);
        map.put("user_name", "minhtran");
        this.userMapper.deleteByMap(map);
    }

    @Test
    public void testDeleteByQueryWrapper() {
        User user = User.builder().userName("minhtran").name("Minh Tran").build();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        this.userMapper.delete(queryWrapper);
    }

    @Test
    public void testDeleteByBatchId() {
        List<Integer> listId = List.of(4,5);
        this.userMapper.deleteBatchIds(listId);
    }

    @Test
    public void testSelectById() {
        User user = this.userMapper.selectById(5L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchId() {
        List<Integer> listId = List.of(4,5);
        List<User> users = this.userMapper.selectBatchIds(listId);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 20);
        User user = this.userMapper.selectOne(queryWrapper);
        System.out.println(user);

        // if result > 2, org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.TooManyResultsException:
        // Expected one result (or null) to be returned by selectOne(), but found: 2
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 21);
        Integer integer = this.userMapper.selectCount(queryWrapper);
        System.out.println("Count: " + integer);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 21);
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testPagination() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 21);

        Page<User> page = new Page<>(1,2);
        IPage<User> iPage = this.userMapper.selectPage(page, queryWrapper);
        System.out.println("Total: " + iPage.getTotal());
        System.out.println("Page: " + iPage.getPages());
        System.out.println("Current: " + iPage.getCurrent());
        System.out.println("Size: " + iPage.getSize());

        List<User> records = iPage.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    public void testBulkData() {
        User user = null;
        for (int i = 0; i < 10; i++) {
            user = User.builder()
                    .userName("Vayne " + ++i).name("Vayne" + ++i).age(21).password("1234").build();
            this.userMapper.insert(user);
        }
    }








}
