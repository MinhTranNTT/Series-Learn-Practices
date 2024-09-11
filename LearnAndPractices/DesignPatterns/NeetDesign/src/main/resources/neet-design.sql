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

-- 
use neetdesign;

select * from schedule_setting;
select * from t_schedule_task;

# create database if not exists neetdesign;
# use neetdesign;

create table user(
                     id int unsigned primary key auto_increment comment 'ID',
                     name varchar(100) comment 'name',
                     age tinyint unsigned comment 'age',
                     gender tinyint unsigned comment 'Gender, 1: male, 2: female',
                     phone varchar(11) comment 'Phone number'
) comment 'user table';

insert into user(id, name, age, gender, phone) VALUES (null,'Apple',55,'1','18800000000');
insert into user(id, name, age, gender, phone) VALUES (null,'Orange',45,'1','18800000001');
insert into user(id, name, age, gender, phone) VALUES (null,'Banana',38,'1','18800000002');
insert into user(id, name, age, gender, phone) VALUES (null,'Cherry',42,'2','18800000003');
insert into user(id, name, age, gender, phone) VALUES (null,'Watermelon',37,'1','18800000004');
insert into user(id, name, age, gender, phone) VALUES (null,'Lemon',48,'1','18800000005');

select * from user;







