package com.example.ontheway.service;

import com.example.ontheway.dao.LoginTicketMapper;
import com.example.ontheway.dao.UserMapper;
import com.example.ontheway.entity.LoginTicket;
import com.example.ontheway.entity.User;
import com.example.ontheway.util.CommunityConstant;
import com.example.ontheway.util.CommunityUtil;
import com.example.ontheway.util.MailClient;
import com.example.ontheway.util.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.management.ObjectName;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserService implements CommunityConstant {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

//    @Autowired
//    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${community.path.domain}")
    private String domain;

    @Autowired
    private RedisTemplate redisTemplate;

    //根据userId查找用户
    public User findById(Integer id) {
//        return userMapper.selectById(id);
        User user=getCatch(id);
        if(user==null) {
            user=initCatch(id);
        }
        return user;
    }

    //根据用户名查找用户
    public User findByName(String username) {
        return userMapper.selectByName(username);
    }
    //1.优先从缓存中取数据
    private User getCatch(int userId) {
        String redisKey=RedisKeyUtil.getUserKey(userId);
        return (User) redisTemplate.opsForValue().get(redisKey);
    }

    //2.取不到的时候初始化缓存数据
    private User initCatch(int userId) {
        String redisKey=RedisKeyUtil.getUserKey(userId);
        User user=userMapper.selectById(userId);
        redisTemplate.opsForValue().set(redisKey,user,3600, TimeUnit.SECONDS);
        return user;
    }
    //3.数据变更时清除缓存数据
    public void clearCache(int userId) {
        String redisKey=RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }

    //注册用户
    public Map<String,Object> register(User user)throws IllegalAccessException {
        Map<String,Object> map=new HashMap<>();
        //空值
        if(user==null) {
            throw new IllegalAccessException("参数不能为空");
        }
        if(StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg","账号不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg","密码不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg","邮箱不能为空");
            return map;
        }
        //验证用户名
        User user1=userMapper.selectByName(user.getUsername());
        if(user1!=null) {
            map.put("usernameMsg","账号已存在，不能重复注册");
            return map;
        }
        //验证邮箱
        user1=userMapper.selectByEmail(user.getEmail());
        if(user1!=null) {
            map.put("emailMsg","邮箱已被注册");
            return map;
        }
        //注册用户
        user.setUsername(user.getUsername());
        //对密码进行加密存储
//        //获取等长的随机字符
//        String s= CommunityUtil.generateUUID().substring(0,5);
        user.setPassword(CommunityUtil.md5(user.getPassword()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.pog",new Random().nextInt(1000)));
        userMapper.insertUser(user);

        //激活邮件
        Context context=new Context();
        context.setVariable("email",user.getEmail());
        //http://localhost:8080/ontheway/activation/101/code
        String url=domain+contextPath+"/activation/"+user.getId()+"/"+user.getActivationCode();
        context.setVariable("url",url);
        String content=templateEngine.process("/mail/activation",context);
        mailClient.sendMail(user.getEmail(),"激活账号",content);
        return map;
    }

    //激活操作
    public int activation(int userId,String code) {
        User user=userMapper.selectById(userId);
        if(user.getStatus()==1) {
            //重复激活
            return CommunityConstant.ACTIVATION_REPEAT;
        }else if(user.getActivationCode().equals(code)) {
            //激活成功，将状态码改为1
            userMapper.updateStatus(userId,1);
            clearCache(userId);
            return CommunityConstant.ACTIVATION_SUCCESS;
        }else {
            return CommunityConstant.ACTIVATION_FAILURE;
        }
    }

    //登录操作
    public Map<String,Object> login(String username,String password,long expiredSeconds) {
        Map<String, Object> map=new HashMap<>();
        //空值处理
        if(StringUtils.isBlank(username)) {
            map.put("usernameMsg","账号不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)) {
            map.put("passwordMsg","密码不能为空");
            return map;
        }
        //验证操作
        User user=userMapper.selectByName(username);
        if(user==null) {
            map.put("usernameMsg","账号不存在");
            return map;
        }
        //验证状态
        if (user.getStatus()==0) {
            map.put("usernameMsg","账号未激活");
            return map;
        }
        //验证密码
        if(!user.getPassword().equals(CommunityUtil.md5(password))) {
            map.put("passwordMsg","密码错误");
            return map;
        }
        //生成登录凭证,在数据库中插入相关信息
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(CommunityUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
//        loginTicketMapper.insertLoginTicket(loginTicket);
        String redisKey= RedisKeyUtil.getTicketKay(loginTicket.getTicket());
        redisTemplate.opsForValue().set(redisKey,loginTicket);
        map.put("ticket",loginTicket.getTicket());
        return map;
    }
    //退出登录
    public void logout(String ticket) {
//        loginTicketMapper.updateStatus(ticket,1);
        String redisKey= RedisKeyUtil.getTicketKay(ticket);
        LoginTicket loginTicket= (LoginTicket) redisTemplate.opsForValue().get(redisKey);
        loginTicket.setStatus(1);
        redisTemplate.opsForValue().set(redisKey,loginTicket);

    }
    //查询凭证
    public LoginTicket findLoginTicket(String ticket) {
        //        return loginTicketMapper.selectByTicket(ticket);
        String redisKey= RedisKeyUtil.getTicketKay(ticket);
        return (LoginTicket) redisTemplate.opsForValue().get(redisKey);
    }
    //更新头像
    public int updateHeader(Integer userId,String headerUrl) {
//        return userMapper.updateHeader(userId,headerUrl);
        int rows=userMapper.updateHeader(userId,headerUrl);
        clearCache(userId);
        return rows;
    }
    //修改密码
    public Map<String,Object> updatePassword(String username,String email,String newPassword) {
        Map<String,Object> map=new HashMap<>();
        if(StringUtils.isBlank(username)) {
            map.put("usernameMsg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(email)) {
            map.put("emailMsg","邮箱不能为空");
            return map;
        }
        if(StringUtils.isBlank(newPassword)) {
            map.put("newPasswordMsg","新密码不能为空");
            return map;
        }
        //验证邮箱
        User user=userMapper.selectByName(username);
        if(user==null) {
            map.put("usernameMsg","此用户不存在");
            return map;
        }
        if(!user.getEmail().equals(email)) {
            map.put("emailMsg","邮箱输入有误");
            return map;
        }

        //更新密码
        newPassword=CommunityUtil.md5(newPassword);
        userMapper.updatePassword(user.getId(),newPassword);
        return map;
    }

}
