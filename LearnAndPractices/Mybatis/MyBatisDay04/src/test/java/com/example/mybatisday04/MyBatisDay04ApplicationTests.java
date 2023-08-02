package com.example.mybatisday04;

import com.example.mybatisday04.mapper.EmployeeMapper;
import com.example.mybatisday04.pojo.Employee;
import com.example.mybatisday04.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.example.mybatisday04.mapper")
class MyBatisDay04ApplicationTests {
    @Autowired private EmployeeMapper employeeMapper;

    @Test
    public void getListEmployee() {
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);
    }
}
