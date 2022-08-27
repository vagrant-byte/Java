package com.example.ontheway.service;

import com.example.ontheway.OnthewayApplication;
import com.example.ontheway.entity.Comment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OnthewayApplication.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    void selectCommentEntity() {
        System.out.println(commentService.selectCommentEntity(1,1,0,10));
    }

    @Test
    void insertComment() {

    }
}