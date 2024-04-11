create database neetdesign;
use neetdesign;

create table schedule_setting
(
    id               varchar(32)  not null comment 'unique id' primary key,
    job_id           varchar(64)  null comment 'Task ID',
    cron_expression  varchar(255) null comment 'cron expression',
    job_result       varchar(32)  null comment 'Task result (rejected through review)',
    create_date      datetime     null comment 'Creation time',
    status           varchar(4)   null,
    create_by        varchar(64)  null comment 'creator account',
    creator          varchar(64)  null comment 'creator',
    version          bigint(19)   null comment 'version number'
) comment 'scheduled task list';

select * from schedule_setting;

-- NEW PROJECT
create table t_schedule_task(
    id int auto_increment primary key not null comment 'Primary key Id',
    task_clazz varchar(200) not null comment 'Scheduled tasks',
    task_method varchar(200) not null comment 'Scheduled task execution method',
    cron varchar(200) not null comment 'Scheduled task time',
    status smallint not null default 0 comment 'Scheduled task status, 0: on, 1: off'
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 comment 'Scheduled task management table';

select * from t_schedule_task;









