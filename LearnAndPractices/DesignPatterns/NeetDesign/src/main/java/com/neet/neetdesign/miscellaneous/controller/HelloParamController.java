package com.neet.neetdesign.miscellaneous.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloParamController {

    // http://localhost:8080/hello/v1?names=Minh&names=Tran
    // http://localhost:8080/hello/v1?names=Minh,Tran
    // http://localhost:8080/hello/v1?names=Minh%2cTran
    @GetMapping("/v1")
    public String getHello(@RequestParam(name = "names") String[] names) {
        String string = Arrays.toString(names);
        log.info(string);
        return string;
    }

    @GetMapping("/v2")
    public String getHello2(@RequestParam(name = "names") String names) {
        log.info(names);
        return names;
    }

    @GetMapping("/v3")
    public String getHello3(@RequestParam(name = "names") List names) {
        log.info(names.toString());
        return names.toString();
    }

    @GetMapping("/v4")
    public String getHello4(@RequestBody StudentBad studentBad) {
        log.info(studentBad.toString());
        return studentBad.toString();
    }

    @PostMapping("/v5")
    public String getHello5(@RequestPart("file") MultipartFile file) {
        // file.transferTo(new File("D:/temp/" + file.getOriginalFilename()));
        return "OK GET FILE SUCCESS: " + file.getOriginalFilename();
    }

    @GetMapping("/v6")
    public String getHello6(HttpServletRequest request, HttpServletResponse response) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent;
    }

    @GetMapping("/v7")
    public String getHello7(@RequestHeader("User-Agent") String userAgent) {
        return userAgent;
    }

    public static void main(String[] args) {
        System.out.println((0.1 + 0.2) == 0.3);
        System.out.println(0.1 + 0.2);

        BigDecimal bigDecimal1 = new BigDecimal("0.1");
        BigDecimal bigDecimal2 = new BigDecimal("0.2");
        BigDecimal add = bigDecimal1.add(bigDecimal2);
        System.out.println(add);

        System.out.println("-------------------");
        BigDecimal bigDecimal4 = new BigDecimal(0.1);
        BigDecimal bigDecimal5 = new BigDecimal(0.2);
        BigDecimal add1 = bigDecimal4.add(bigDecimal5);
        System.out.println(add1);
    }

}
