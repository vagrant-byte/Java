package com.example.ontheway.dao;

import com.example.ontheway.entity.Comment;
import com.example.ontheway.util.CommunityConstant;
import org.junit.Test;

import com.example.ontheway.OnthewayApplication;
import com.example.ontheway.entity.DiscussPost;
import com.example.ontheway.entity.LoginTicket;

import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OnthewayApplication.class)

class CommentMapperTest implements CommunityConstant {
    @Autowired
    private CommentMapper commentMapper;

    @Test
    void insertComment() {
        Comment comment=new Comment();
        comment.setUserId(2);
        comment.setEntityType(ENTITY_TYPE_COMMENT);
        comment.setEntityId(1);
        comment.setTargetId(1);
        comment.setContent("哈哈哈哈哈");
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentMapper.insertComment(comment);

    }
}