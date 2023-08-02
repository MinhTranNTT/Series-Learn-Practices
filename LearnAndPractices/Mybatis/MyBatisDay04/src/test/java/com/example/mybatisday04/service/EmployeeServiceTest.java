package com.example.mybatisday04.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisday04.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@MapperScan("com.example.mybatisday04.mapper")
@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getEmployeeByName() {
        List<Employee> employees = employeeService.listAllByLastName("tom");
        employees.forEach(System.out::println);
    }

    @Test
    public void saveEmployee1() {
        Employee employee = new Employee();
        employee.setLastName("Trump");
        employee.setEmail("trump@qq.com");
        employee.setAge(21);
        employeeService.save(employee);
    }

    @Test
    public void saveEmployee2() {
        Employee employee = Employee.builder()
                .lastName("Stephen")
                .email("stephen@qq.com")
                .age(22)
                .gmtCreate(LocalDateTime.now())
                .gmtModified(LocalDateTime.now()).build();
        employeeService.save(employee);
    }

    @Test
    public void testSaveBulkEmployee() {
        Employee employee = null;
        for (int i = 0; i < 10; i++) {
            employee = Employee.builder()
                    .age(i+2)
                    .lastName("Vayne "+i)
                    .email("vayne" + i + "@gmail.com")
                    .build();
            employeeService.save(employee);
        }
    }

    @Test
    public void updateByEmployeeId() {
        Employee employee = Employee.builder()
                .id(1L)
                .lastName("jacky")
                .gmtModified(LocalDateTime.now()).build();
        employeeService.updateById(employee);
    }

    @Test
    public void testInsertEmployeeWithMetaObject() {
        Employee employee = Employee.builder()
                .lastName("Jax1")
                .email("jax1@gmail.com")
                .age(22).build();
        employeeService.save(employee);
    }

    @Test
    public void testSoftDeleteByLogic() {
        employeeService.removeById(7);
    }

    @Test
    public void testLoadInterceptor() {
        Page<Employee> page = new Page<>(1,5);
        employeeService.page(page, null);
        List<Employee> employeeList = page.getRecords();
        employeeList.forEach(System.out::println);
        System.out.println("getTotal() :" + page.getTotal());
        System.out.println("getCurrent() :" + page.getCurrent());
        System.out.println("getPages() :" + page.getPages());
        System.out.println("getSize() :" + page.getSize());    // number date in a page
        System.out.println("hasPrevious() :" + page.hasPrevious());
        System.out.println("hasNext() :" + page.hasNext());
    }

    @Test
    public void testLoadInterceptor2() {
        Page<Employee> page = new Page<>(2,5);
        employeeService.page(page, null);
        List<Employee> employeeList = page.getRecords();
        employeeList.forEach(System.out::println);
        System.out.println("getTotal() :" + page.getTotal());
        System.out.println("getCurrent() :" + page.getCurrent());
        System.out.println("getPages() :" + page.getPages());
        System.out.println("getSize() :" + page.getSize());    // number date in a page
        System.out.println("hasPrevious() :" + page.hasPrevious());
        System.out.println("hasNext() :" + page.hasNext());
    }

    @Test
    public void testLoadInterceptor3() {
        Page<Employee> page = new Page<>(1, 5);
        employeeService.page(page, new QueryWrapper<Employee>()
                .between("age", 2, 11)
                .eq("gender", 1));
        List<Employee> employeeList = page.getRecords();
        employeeList.forEach(System.out::println);
    }

}

