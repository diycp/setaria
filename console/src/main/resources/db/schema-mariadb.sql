CREATE DATABASE `setaria` CHARACTER SET utf8 COLLATE utf8_bin ;
USE `setaria` ;

-- create user --
CREATE USER 'setaria'@'%' IDENTIFIED BY 'setaria';

FLUSH PRIVILEGES ;

GRANT ALL PRIVILEGES ON setaria.* TO 'setaria'@'%' WITH GRANT OPTION;
