show databases;
drop database if exists blog;
create database blog;
use blog;

show tables;

select id, category_name, category_code
from category;

select *
from category;

create table category
(
    id            int auto_increment
        primary key,
    category_name varchar(50) null,
    category_code varchar(50) null
)
    collate = utf8mb4_general_ci;


INSERT INTO blog.category (id, category_name, category_code) VALUES (1, 'Java', 'JAVA');
INSERT INTO blog.category (id, category_name, category_code) VALUES (2, 'CSharp', 'C#');
INSERT INTO blog.category (id, category_name, category_code) VALUES (3, 'Javascript', 'JS');


# default user.ddl
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT IGNORE INTO `users` VALUES ('user', '{noop}123456', '1');
INSERT IGNORE INTO `authorities` VALUES ('user', 'read');

INSERT IGNORE INTO `users` VALUES ('admin', '{bcrypt}$2a$12$SBQ6B4WuzbNaMYdU0sGvYuy1z/iCoq0Ll29.meIBndugoYezBwoL6', '1');
INSERT IGNORE INTO `authorities` VALUES ('admin', 'admin');

select * from users;
select * from authorities;

CREATE TABLE `customer` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `email` varchar(45) NOT NULL,
                            `pwd` varchar(200) NOT NULL,
                            `role` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
);

INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES
('user@example.com', '{noop}123456', 'read'),
('admin@example.com', '{bcrypt}$2a$12$SBQ6B4WuzbNaMYdU0sGvYuy1z/iCoq0Ll29.meIBndugoYezBwoL6', 'admin');

select * from customer;
select id, email, pwd, role
from customer;

