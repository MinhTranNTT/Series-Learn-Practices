package com.example.mybatisday04.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisday04.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {
    List<Employee> selectAllByLastName(@Param("lastName") String lastName);
}
