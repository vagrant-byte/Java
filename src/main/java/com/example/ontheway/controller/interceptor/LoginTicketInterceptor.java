package com.example.ontheway.controller.interceptor;

import com.example.ontheway.entity.LoginTicket;
import com.example.ontheway.entity.User;
import com.example.ontheway.service.UserService;
import com.example.ontheway.util.CookieUtil;
import com.example.ontheway.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

// 显示登录状态使用拦截器进行
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;

    //在controller之前进行拦击
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取凭证
        String ticket= CookieUtil.getValue(request,"ticket");
        if(ticket!=null) {
            //查询凭证
            LoginTicket loginTicket=userService.findLoginTicket(ticket);
            //校验凭证是否有效 0-有效  失效时间在当前时间之后
            if(loginTicket!=null&&loginTicket.getStatus()==0&&loginTicket.getExpired().after(new Date())) {
                //根据凭证查询用户
                User user=userService.findById(loginTicket.getUserId());
                //在本次请求中持有用户  服务器会获取浏览器的多个用户 存在线程安全问题 使用ThreadLocal 将每个用户隔离进行存储
                hostHolder.setUser(user);
            }
        }
        return true;
    }

    //在模板引擎之前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user=hostHolder.getUser();
        if(user!=null&&modelAndView!=null) {
            modelAndView.addObject("loginUser",user);
        }
    }
    //在controller之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
