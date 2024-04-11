package com.neet.neetdesign.miscellaneous.dynamicschedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulingConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(corePoolSize);
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setThreadNamePrefix("AntiFraudSchedulerThreadPool-");
        return taskScheduler;
    }
}
