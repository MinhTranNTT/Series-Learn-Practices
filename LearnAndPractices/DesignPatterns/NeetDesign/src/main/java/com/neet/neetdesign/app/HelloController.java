package com.neet.neetdesign.app;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/hello")
public class HelloController {

    /*@PostMapping("")
    public String getHello(@RequestBody Student student) {
        System.out.println(student);
        return "Hello";
    }*/

    @GetMapping("")
    public String getHello(@RequestBody Student student) {
        System.out.println(student);
        return "Hello";
    }

    // public static void main(String[] args) {
    //     Student student = new Student();
    //     student.setId(10);
    //     student.setName("MinhTran");
    //     student.setAge("20");
    //
    //     Map<String, Object> map = new HashMap<>();
    //     map.put("data", student);
    //     String json = new Gson().toJson(map);
    //     System.out.println(json);
    // }

    public static void main(String[] args) {
        Executor executor = command -> {
            System.out.println("kakaka");
        };
    }

}
