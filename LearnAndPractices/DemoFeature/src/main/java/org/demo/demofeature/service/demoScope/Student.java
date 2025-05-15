package org.demo.demofeature.service.demoScope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Slf4j
public class Student {
    private String name;
    private Integer age;

    public Student() {
        log.info("Constructor Student");
    }
}
