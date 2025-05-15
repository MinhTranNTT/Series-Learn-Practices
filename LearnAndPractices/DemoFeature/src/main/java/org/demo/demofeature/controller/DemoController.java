package org.demo.demofeature.controller;

import lombok.extern.slf4j.Slf4j;
import org.demo.demofeature.service.DemoService;
import org.demo.demofeature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/demo")
public class DemoController {
    // @Autowired
    // private DemoService demoService;
    private DemoService demoService;
    private UserService userService;

    public DemoController() {
    }

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    public DemoController(DemoService demoService, UserService userService) {
        this.demoService = demoService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getDemo() {
        demoService.run();
        log.info("DemoController");
        return "Hello World";
    }
}
