package com.example.way02.controller;

import com.example.way02.advice.ResponseResultBody;
import com.example.way02.exception.ResultException;
import com.example.way02.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/helloResult")
@ResponseResultBody
@Slf4j
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

    // private boolean isPerformTask7 = false;
    // private boolean isPerformTask15 = false;
    //
    // @Scheduled(fixedRate = 3000)
    // public void performTask7() {
    //     System.out.println("Scheduled task executed at 3s");
    //     isPerformTask7 = true;
    //     this.checkBothDone();
    //     isPerformTask7 = false;
    // }
    //
    // private void checkBothDone() {
    //     if (isPerformTask7 && isPerformTask15) {
    //         System.out.println("Done both function");
    //     } else {
    //         System.out.println("Not Done both function");
    //     }
    // }

    // @Scheduled(fixedRate = 10000)
    // public void performTask10() {
    //     System.out.println("Scheduled task executed at 10s");
    //     isPerformTask15 = true;
    // }

    private CountDownLatch latch = new CountDownLatch(2);

    @Scheduled(fixedRate = 10000)
    public void performTask7() throws InterruptedException {
        log.info("Scheduled task executed at 10s");
        // TimeUnit.SECONDS.sleep(7);
        latch.countDown();
        checkBothDone();
    }

    @Scheduled(fixedRate = 60000)
    public void performTask10() {
        log.info("Scheduled task executed at 1p");
        latch.countDown();
        // checkBothDone();
    }

    private void checkBothDone() {
        if (latch.getCount() == 0) {
            log.info(".Done");
            // latch = new CountDownLatch(2);
        } else {
            log.info(".Not done");
        }
        latch = new CountDownLatch(2);
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