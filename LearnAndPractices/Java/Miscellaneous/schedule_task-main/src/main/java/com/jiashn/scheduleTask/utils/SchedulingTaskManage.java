package com.jiashn.scheduleTask.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

// Scheduled task management method
@Component
@Slf4j
public class SchedulingTaskManage {

    // Put tasks into map for easier management
    public static ConcurrentHashMap<String, ScheduledFuture<?>> cache = new ConcurrentHashMap<>();

    @Resource(name = "taskSchedulerPool")
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * Delete scheduled tasks
     * @param key Custom scheduled task name
     */
    public void stopSchedulingTask(String key){
        if (Objects.isNull(cache.get(key))){
            log.info(String.format(".......There is no scheduled task for the current key 【%s】......",key));
            return;
        }
        ScheduledFuture<?> future = cache.get(key);
        if (Objects.nonNull(future)){
            //Close the current scheduled task
            future.cancel(Boolean.TRUE);
            cache.remove(key);
            log.info(String.format("The scheduled task corresponding to 【%s】 was deleted successfully.",key));
        }
    }

    /**
     * Create a scheduled task
     * @param key Task key
     * @param runnable current thread
     * @param cron scheduled task cron
     */
    public void createSchedulingTask(String key, SchedulingTaskRunnable runnable, String cron){
        this.stopSchedulingTask(key);
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(cron));
        assert schedule != null;
        cache.put(key,schedule);
        log.info(String.format("【%s】Scheduled task created successfully",key));
    }
}
