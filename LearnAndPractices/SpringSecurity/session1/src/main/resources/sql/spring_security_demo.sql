-- CREATE DATABASE spring_security;
USE spring_security;

-- create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
-- create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
-- create unique index ix_auth_username on authorities (username,authority);

-- INSERT IGNORE INTO `users` VALUES ('user', '{noop}CauBeButChi@2024!!!', '1');
INSERT IGNORE INTO `authorities` VALUES ('user', 'read');

-- INSERT IGNORE INTO `users` VALUES ('admin', '{bcrypt}$2a$12$.NDUkFUo2hT4BLq9LXmQluk7YXShmWpIGPybrmVy8KWaf6d9HgeM6', '1');
INSERT IGNORE INTO `authorities` VALUES ('admin', 'admin');

SELECT * FROM `users`;
SELECT * FROM `authorities`;
select username,password,enabled from users where username = 'user';

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES 
('happy@example.com', '{noop}CauBeButChi@2024!!!', 'read'), 
('admin@example.com', '{bcrypt}$2a$12$.NDUkFUo2hT4BLq9LXmQluk7YXShmWpIGPybrmVy8KWaf6d9HgeM6', 'admin');

SELECT * FROM customer;