package com.example.way02.controller;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/error")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Exceptions in Java")
public class HelloExceptionController {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<String, Object>();
        INFO.put("name", "galaxy cine");
        INFO.put("age", "71");
    }

    @GetMapping()
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloJavaError")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Exceptions in Java")
    public HashMap<String, Object> helloJavaError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new MyException();
    }
}

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Customized exceptions")
class MyException extends Exception {

}
