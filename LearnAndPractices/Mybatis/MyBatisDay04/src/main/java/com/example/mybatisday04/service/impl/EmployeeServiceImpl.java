package com.example.mybatisday04.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisday04.mapper.EmployeeMapper;
import com.example.mybatisday04.pojo.Employee;
import com.example.mybatisday04.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public List<Employee> listAllByLastName(String lastName) {
        return baseMapper.selectAllByLastName(lastName);
    }

    @Override
    public List<Employee> listAllByLstId(List<Integer> list) {
        return baseMapper.selectAllByLastLstId(list);
    }
}
