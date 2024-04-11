package com.jiashn.scheduleTask.method;

import com.jiashn.scheduleTask.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/2/16 16:24
 **/
@Slf4j
@Component(value = "testSchedulingTask")
public class TestSchedulingTask {

    public void taskMethod(UserInfo obj){
        log.info(String.format("The current scheduled task is called - Output parameters - parameters 1：%s,parameter 2：%s",obj.getUserName(),obj.getPassword()));
    }
}
