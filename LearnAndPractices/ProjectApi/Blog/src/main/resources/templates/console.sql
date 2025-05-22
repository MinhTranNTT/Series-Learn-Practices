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

show tables;

select * from authorities;

DROP TABLE authorities;

CREATE TABLE `authorities` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `customer_id` int NOT NULL,
                               `name` varchar(50) NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `customer_id` (`customer_id`),
                               CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWACCOUNT');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWCARDS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWLOANS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWBALANCE');
DELETE FROM `authorities`;

INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'ROLE_USER');

INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'ROLE_ADMIN');


drop table customer;

CREATE TABLE `customer` (
                            `customer_id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) NOT NULL,
                            `email` varchar(100) NOT NULL,
                            `mobile_number` varchar(20) NOT NULL,
                            `pwd` varchar(500) NOT NULL,
                            `role` varchar(100) NOT NULL,
                            `create_dt` date DEFAULT NULL,
                            PRIMARY KEY (`customer_id`)
);

INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`)
VALUES ('Happy','happy@example.com','5334122365', '{bcrypt}$2a$12$SBQ6B4WuzbNaMYdU0sGvYuy1z/iCoq0Ll29.meIBndugoYezBwoL6', 'admin',CURDATE());
INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`)
VALUES ('Happy','admin@example.com','5334122365', '{bcrypt}$2a$12$SBQ6B4WuzbNaMYdU0sGvYuy1z/iCoq0Ll29.meIBndugoYezBwoL6', 'admin',CURDATE());


select * from customer;
select * from authorities;

select c.customer_id as id, c.email, c.pwd, a.name as role
from customer c
inner join authorities a on c.customer_id = a.customer_id
where email = 'happy@example.com';

select customer_id, email, pwd, role from customer where customer_id = 1;
