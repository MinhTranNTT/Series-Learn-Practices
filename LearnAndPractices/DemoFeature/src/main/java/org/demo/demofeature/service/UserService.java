package org.demo.demofeature.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public void run() {
        log.info("User Service run");
    }
}
