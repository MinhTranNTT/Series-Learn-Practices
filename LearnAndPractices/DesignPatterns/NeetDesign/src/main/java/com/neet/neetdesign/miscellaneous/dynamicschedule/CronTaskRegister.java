package com.neet.neetdesign.miscellaneous.dynamicschedule;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@SuppressWarnings("all")
public class CronTaskRegister implements DisposableBean {
    @Resource private TaskScheduler taskScheduler;

    // save id schedule
    private final Map<String, ScheduledTask> schedulerTaskMap = new ConcurrentHashMap<>(64);

    // add task
    public void addTask(Runnable task, String cronExpression, String jobId) {
        addTask(new CronTask(task, cronExpression), jobId);
    }

    public void addTask(CronTask cronTask, String jobId) {
        if (Objects.isNull(cronTask))   return;
        Runnable task = cronTask.getRunnable();
        if (this.schedulerTaskMap.containsKey(task)) {
            removeTask(jobId);
        }
        this.schedulerTaskMap.put(jobId, scheduleTask(cronTask));
    }

    // cancel scheduled task by job task id
    public void removeTask(String jobId) {
        ScheduledTask schedulerTask = this.schedulerTaskMap.remove(jobId);
        if (Objects.isNull(schedulerTask))  return;
        schedulerTask.cancel();
    }

    public ScheduledTask scheduleTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }

    @Override
    public void destroy() throws Exception {
        this.schedulerTaskMap.values().forEach(ScheduledTask::cancel);
        this.schedulerTaskMap.clear();
    }
}
