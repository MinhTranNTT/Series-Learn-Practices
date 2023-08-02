package com.example.mybatisday04.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisday04.pojo.Employee;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
    List<Employee> listAllByLastName(String lastName);
}
