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
