package org.crocodile.apptour.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/advice")
public class DemoAdviceController {

    @GetMapping("getModel")
    public Map<String, Object> modelInfo(Model model) {
        Map<String, Object> modelMap = model.asMap();
        return Map.of("data", modelMap.get("mapList"), "code", "200", "msg", "Thành công");
    }

    @GetMapping("/exception")
    public void throwException() throws Exception {
        throw new Exception("Ngoại lệ không xác định");
    }

}
