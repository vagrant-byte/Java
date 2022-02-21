create database if not exists oj_databases;
use oj_databases;

drop table if exists oj_table;
create table oj_table(
     id int primary key auto_increment,
--      title表示题目标题
     title varchar(50),
--      level表示难度
     level varchar(20),
--      description表示题目描述
     description varchar(4096),
--      templateCode表示代码模板
     templateCode varchar(4096),
--      testCode表示测试用例代码
     testCode varchar(4096)
);

drop table if exists user;
create table user(
    userId int primary key auto_increment,
    username varchar(128),
    password varchar(128)
);

