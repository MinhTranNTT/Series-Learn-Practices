package org.crocodile.apptour.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/example")
public class ExampleController {
    @GetMapping("/info")
    public Map<String, Object> getInfo(String v) {
        Map<String, Object> response = new LinkedHashMap<>();
        List<Map<String, Object>> listA = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", i);
            map.put("name", "A-xl-" + i);
            map.put("sex", "Nam");
            map.put("createTime", new Date());
            listA.add(map);
        }

        response.put("list-A", listA);
        response.put("status", "200");
        response.put("msg", "Yêu cầu thành công");
        return response;
    }

    @GetMapping("/error")
    public Map<String, Object> getError(String v) {
        Map<String, Object> response = new LinkedHashMap<>();
        List<Map<String, Object>> listA = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", i);
            map.put("name", "A-xl-" + i);
            map.put("sex", "Nam");
            map.put("createTime", new Date());
            listA.add(map);
        }

        response.put("list-A", listA);
        response.put("status", "200");
        response.put("msg", "Yêu cầu thành công");
        int i = 5 / 0; // Phát sinh lỗi chia cho 0
        return response;
    }
}
