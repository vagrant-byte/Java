create database if not exists java0921;

use java0921;

drop table if exists book;
create table book(
         id int primary key auto_increment,
         name varchar(20),
         author varchar(20),
         price int,
         type varchar(20),
          --如果isBorrow为1表示已借出 为0表示未借出
         isBorrow int
);

drop table if exists user;
create table user(
    userId int primary key auto_increment,
    username varchar(20),
    password varchar(20),
     --如果isAdmin为1表示管理员 为0表示普通用户
    isAdmin int
);

--插入一些数据
insert into book values(null,'西游记','吴承恩',10000,'古典小说',0);
insert into book values(null,'水浒传','施耐庵',10000,'古典小说',0);
insert into book values(null,'红楼梦','曹雪芹',10000,'古典小说',0);
insert into book values(null,'三国演义','罗贯中',10000,'古典小说',0);

insert into user values(null,'Admin','123',1);
insert into user values(null,'张三','123',0);