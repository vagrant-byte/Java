package com.example.ontheway.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {
    private int id;
    private int userId;
    //评论帖子的类型
    private int entityType;
    //帖子Id
    private int entityId;
    //评论指定用户的Id
    private int targetId;
    //内容
    private String content;
    //状态
    private int status;
    //创建时间
    private Date createTime;
}
