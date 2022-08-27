package com.example.ontheway.dao;

import com.example.ontheway.OnthewayApplication;
import com.example.ontheway.entity.DiscussPost;
import com.example.ontheway.entity.LoginTicket;

import org.junit.Test;
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
class DiscussPostMapperTest {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;
    
    @Test
    void selectDiscussPost() {
        List<DiscussPost> discussPost=discussPostMapper.selectDiscussPost(1,0,10);
        for (DiscussPost discussPost1:discussPost) {
            System.out.println(discussPost1);
        }
    }

    @Test
    void selectDiscussPostRows() {
        int col=discussPostMapper.selectDiscussPostRows(1);
        System.out.println(col);
    }

    @Test
    void insertTicket() {
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket.getId());

        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket.getId());
    }

    @Test
    void insertDiscussPost() {
        DiscussPost discussPost=new DiscussPost();
        discussPost.setUserId(12);
        discussPost.setTitle("夏天十个放心去旅游的城市");
        discussPost.setContent("1.烟台 2.贵阳 3.丽江 4.承德 5.青岛 6.西宁 7.哈尔滨 8.大连 9.昆明 10.秦皇岛");
        discussPost.setType(0);
        discussPost.setStatus(0);
        discussPost.setCreateTime(new Date());
        discussPost.setCommentCount(0);
        discussPost.setScore(80);
        discussPostMapper.insertDiscussPost(discussPost);
    }

    @Test
    void selectDiscussPostById() {
    }

    @Test
    void updateCommentCount() {
    }
}