package com.example.ontheway.service;

import com.example.ontheway.OnthewayApplication;
import com.example.ontheway.entity.Message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Action;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OnthewayApplication.class)
class MessageServiceTest {

    @Autowired
    private MessageService messageService;
    @Test
    void selectConversations(){
        System.out.println(messageService.selectConversations(12,0,10));
    }

    @Test
    void  add() {
        Message message=new Message();
        message.setFromId(111);
        message.setToId(112);
        message.setContent("你好");
        message.setStatus(1);
        message.setConversationId("111_112");
        message.setCreateTime(new Date());
        messageService.addMessage(message);
    }
}