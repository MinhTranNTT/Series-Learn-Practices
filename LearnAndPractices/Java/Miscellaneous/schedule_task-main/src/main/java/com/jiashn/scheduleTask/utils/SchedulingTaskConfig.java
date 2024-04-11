package com.jiashn.scheduleTask.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

// You need to add the ThreadPoolTaskScheduler thread pool to use it normally through @Autowired
@Configuration
public class SchedulingTaskConfig {

    @Bean(name = "taskSchedulerPool")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // Set thread pool size
        taskScheduler.setPoolSize(60);
        // Thread name prefix
        taskScheduler.setThreadNamePrefix("Thread-Pool-");
        // Set the maximum waiting time for termination, unit second, that is,
        // when shutting down, you need to wait for other tasks to complete execution.
        taskScheduler.setAwaitTerminationSeconds(3000);
        // Set whether to wait for scheduled tasks to complete when shutting down,
        // not interrupt running tasks and execute all tasks in the queue. Default is false.
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        return taskScheduler;
    }
}
