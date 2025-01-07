package com.example.mybatisday04.controller;

import com.example.mybatisday04.pojo.Employee;
import com.example.mybatisday04.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("")
    public void getABC(String ids) {
        List<Employee> employees = getListByListId(ids);
        System.out.println(employees);

        List<Employee> employees2 = getListEmployee();
        employees2.forEach(System.out::println);

    }

    private List<Employee> getListByListId(String ids) {
        List<Integer> lstId = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        //        List<Integer> lstId = Arrays.asList(1,2);
        List<Employee> employees = employeeService.listAllByLstId(lstId);
        return employees;
    }

    private List<Employee> getListEmployee() {
        List<Employee> employees = employeeService.listAllByLastName("tom");
        return employees;
    }

    // @GetMapping("/pagination")
    // public ResponseEntity<?> getListPagination(@RequestParam(value = "pageNumber", defaultValue = "1")
    //                                             Integer pageNumber) {
    //     Map<String, Object> rs = new HashMap<>();
    //     PageHelper.startPage(pageNumber, 5);
    //     List<Employee> employees = employeeService.listAllByLastName("Vayne");
    //     PageInfo<Employee> page = new PageInfo<>(employees, 5);
    //     // rs.put("data", employees);
    //     rs.put("page", page);
    //     return ResponseEntity.status(HttpStatus.OK).body(rs);
    // }
}
