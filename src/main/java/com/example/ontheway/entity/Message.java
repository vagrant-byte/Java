package com.example.ontheway.entity;

import javax.xml.crypto.Data;
import java.util.Date;

@lombok.Data
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    // 状态 0-未读 1-已读 2-删除
    private int status;
    private Date createTime;
}
