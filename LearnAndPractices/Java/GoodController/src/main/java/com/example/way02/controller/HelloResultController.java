package com.example.way02.controller;

import com.example.way02.advice.ResponseResultBody;
import com.example.way02.exception.ResultException;
import com.example.way02.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/helloResult")
@ResponseResultBody
public class HelloResultController {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy cinema");
        INFO.put("age", "70");
    }

    @GetMapping("hello")
    public HashMap<String, Object> hello() {
        return INFO;
    }

    @GetMapping("result")
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new ResultException();
    }

    @GetMapping("resultStudent")
    public Result<?> helloStudent() {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student(1, "A");
        Student student2 = new Student(2, "B");
        Student student3 = new Student(3, "C");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        return Result.success(list);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Student {
    private int id;
    private String name;
}