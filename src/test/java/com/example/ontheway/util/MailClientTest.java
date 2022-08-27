package com.example.ontheway.util;

import com.example.ontheway.OnthewayApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.naming.*;

import java.util.Hashtable;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OnthewayApplication.class)
class MailClientTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    void sendMail() {
        mailClient.sendMail("1164733566@qq.com","TEXT","hello");
    }

    @Test
    void testHtmlMail() {
        Context context=new Context();
        context.setVariable("username","sunday");


    }
}