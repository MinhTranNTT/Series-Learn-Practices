package org.crocodile.apptour.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoInterceptorController {

    @GetMapping("/interceptor")
    public Map<String, Object> interceptor() {
        return Map.of("code", "200", "msg", "Yêu cầu thành công", "data", Map.of("id", "324354", "name", "Kiểm tra chặn"));
    }

    @GetMapping("/info")
    public Map<String, Object> getInterceptor() {
        return Map.of("code", "200", "msg", "Yêu cầu thành công", "data", "Không bị chặn");
    }

}
