
create database if not exists travel;

use travel;

drop table if exists user ;
create table user (
     id int(11) primary key auto_increment,
     username varchar(128) default null,
     password varchar(128) default null,
     email varchar(50) default null,
     type int(11) default null comment '0-普通用户;1-超级管理员;2-版主;',
     status int(11) default null comment '0-未激活;1-已激活',
--      激活代码
     activationcode varchar(200) default null,
     headerurl varchar(200) default null
);

drop table if exists discuss_post;
-- 评论表
CREATE TABLE `discuss_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `type` int(11) DEFAULT NULL COMMENT '0-普通; 1-置顶;',
  `status` int(11) DEFAULT NULL COMMENT '0-正常; 1-精华; 2-拉黑;',
  `create_time` timestamp NULL DEFAULT NULL,
--   帖子评论的数量
  `comment_count` int(11) DEFAULT NULL,
--   帖子的分数
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 存放登录的凭证信息
drop table if exists login_ticket;
CREATE TABLE `login_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
--   登录的唯一凭证
  `ticket` varchar(45) NOT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0-有效; 1-无效;',
--   登录的失效时间
  `expired` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_ticket` (`ticket`(20))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 评论信息
drop table if exists comment;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
--   评论的类型 是评论当前帖子还是对帖子下的其它帖子进行评论
  `entity_type` int(11) DEFAULT NULL,
--   帖子的id
  `entity_id` int(11) DEFAULT NULL,
--   指向某个对应用户的评论
  `target_id` int(11) DEFAULT NULL,
  `content` text,
  `status` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_entity_id` (`entity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 私信列表
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
--   发件人Id
  `from_id` int(11) DEFAULT NULL,
--   收件人Id
  `to_id` int(11) DEFAULT NULL,
--   用来标识当前的会话双方
  `conversation_id` varchar(45) NOT NULL,
  `content` text,
  `status` int(11) DEFAULT NULL COMMENT '0-未读;1-已读;2-删除;',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_from_id` (`from_id`),
  KEY `index_to_id` (`to_id`),
  KEY `index_conversation_id` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;