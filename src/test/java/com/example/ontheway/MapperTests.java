package com.example.ontheway;

import com.example.ontheway.dao.UserMapper;
import com.example.ontheway.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OnthewayApplication.class)
// 测试完成之后回滚数据(不污染业务数据)
//@Transactional
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectUser() {
        User user=userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user=new User();
        user.setUsername("寒江雪");
        user.setPassword("123456");
        user.setEmail("123456@qq.com");
        user.setType(0);
        user.setStatus(1);
        user.setActivationCode("sss");
        int rows=userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

}
