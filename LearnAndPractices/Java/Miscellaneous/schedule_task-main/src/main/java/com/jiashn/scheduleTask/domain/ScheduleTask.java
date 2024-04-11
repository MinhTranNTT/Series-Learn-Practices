package com.jiashn.scheduleTask.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/6/7 11:08
 **/
@TableName("t_schedule_task")
@Data
public class ScheduleTask {
    public interface Update{};

    @TableId(type = IdType.AUTO)
    @NotNull(message = "Task id cannot be empty",groups = Update.class)
    private Integer id;

    @NotBlank(message = "Please fill in the task execution category")
    private String taskClazz;
    @NotBlank(message = "Please fill in the task execution method")
    private String taskMethod;
    @NotBlank(message = "Please fill in the task execution time in cron format")
    private String cron;

    @TableLogic
    private Integer status;
}
