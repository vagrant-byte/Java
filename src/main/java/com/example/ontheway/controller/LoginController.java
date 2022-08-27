package com.example.ontheway.controller;

import com.example.ontheway.dao.LoginTicketMapper;
import com.example.ontheway.entity.User;
import com.example.ontheway.service.UserService;
import com.example.ontheway.util.CommunityConstant;
import com.example.ontheway.util.CommunityUtil;
import com.example.ontheway.util.RedisKeyUtil;
import com.google.code.kaptcha.Producer;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController implements CommunityConstant {
    private static final Logger logger=LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private Producer kaptchaProduce;

    @Value("${server.servlet.context-path}")
    private String contextpath;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private TemplateEngine templateEngine;
    //    获取注册页面
    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String getRegisterPage() {
        return "/site/register";
    }
    //登录页面
    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getLoginPage() {
        return "/site/login";
    }

    //进行注册操作
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user) throws IllegalAccessException {
        Map<String,Object> map=userService.register(user);
        if(map==null||map.isEmpty()) {
            //map为空说明注册成功
//            Context context=new Context();
//            context.setVariable("msg","你已注册成功，我们已经给你的邮箱发送了一个激活邮件，请尽快激活！");
//            context.setVariable("target","/index");
//            return templateEngine.process("/site/operate-result",context);
           model.addAttribute("msg","你已注册成功，我们已经给你的邮箱发送了一个激活邮件，请尽快激活！");
           model.addAttribute("target","/index");
           return "/site/operate-result";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return "/site/register";
        }
    }
    //邮件激活请求
    @RequestMapping(path = "/activation/{userId}/{code}",method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId,@PathVariable("code") String code) {
        int result=userService.activation(userId,code);
        if(result==ACTIVATION_SUCCESS) {
            model.addAttribute("msg","激活成功，账号可以正常使用");
            model.addAttribute("target","/login");
        }else if(result==ACTIVATION_REPEAT) {
            model.addAttribute("msg","重复操作，该账号已被激活");
            model.addAttribute("target","/index");
        }else {
            model.addAttribute("msg","激活失败");
            model.addAttribute("target","/index");
        }
        return "/site/operate-result";
    }
    //刷新验证码请求
    @RequestMapping(path = "/kaptcha",method = RequestMethod.GET)
   public void getKaptcha(HttpServletResponse response/*, HttpSession session*/) {
        //生成验证码 字符
        String text=kaptchaProduce.createText();
        //根据字符生成对应的图片
        BufferedImage image=kaptchaProduce.createImage(text);
        //将验证码存入session中以便进行验证验证码是否正确
        //session.setAttribute("kaptcha",text);

        //验证码的归属
        String kaptchaOwner= CommunityUtil.generateUUID();
        Cookie cookie=new Cookie("kaptchaOwner",kaptchaOwner);
        cookie.setMaxAge(60);
        cookie.setPath(contextpath);
        response.addCookie(cookie);
        //将验证码存入redis
        String redisKey= RedisKeyUtil.getKaptchaKey(kaptchaOwner);
        redisTemplate.opsForValue().set(redisKey,text,60, TimeUnit.SECONDS);
        //将图片输出给浏览器
        response.setContentType("image/png");
        try{
            OutputStream os=response.getOutputStream();
            //将图片以png格式写入到字节流中
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            logger.error("相应验证码失败:"+e.getMessage());
        }
    }
    //登录操作
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(String username,String password,String code,boolean rememberme,
                      Model model,/*HttpSession session,*/HttpServletResponse response,
                        @CookieValue("kaptchaOwner") String kaptchaOwner) {
        //检查验证码
        //String kaptcha= (String) session.getAttribute("kaptcha");
        String kaptcha=null;
        if(StringUtils.isNotBlank(kaptchaOwner)) {
            String redisKey=RedisKeyUtil.getKaptchaKey(kaptchaOwner);
            kaptcha= (String) redisTemplate.opsForValue().get(redisKey);
        }

        if(StringUtils.isBlank(kaptcha)||StringUtils.isBlank(code)||!kaptcha.equalsIgnoreCase(code)) {
            model.addAttribute("codeMsg","验证码错误");
            return "/site/login";
        }
        //验证账号，密码
        int expiredSeconds=rememberme?REMEMBER_EXPIRED_SECONDS:DEFAULT_EXPIRED_SECONDS;
        Map<String,Object> map=userService.login(username,password,expiredSeconds);
        if(map.containsKey("ticket")) {
            //将登录凭证存入cookie
            Cookie cookie=new Cookie("ticket",map.get("ticket").toString());
            //cookie适用的范围
            cookie.setPath(contextpath);
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            return "redirect:/index";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/login";
        }
    }
    //退出功能
    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/login";
    }
    //忘记密码页面
    @RequestMapping(path = "/forget",method = RequestMethod.GET)
    public String getForgetPage() {
        return "/site/forget";
    }

    // 重置密码
    @RequestMapping(path = "/forget/password", method = RequestMethod.POST)
    public String resetPassword(String username,String email, String password, Model model) {
        Map<String, Object> map = userService.updatePassword(username,email,password);
        if(map==null||map.isEmpty()) {
            return "redirect:/login";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            model.addAttribute("newPasswordMsg",map.get("newPasswordMsg"));
            return "/site/setting";
        }
    }
}
