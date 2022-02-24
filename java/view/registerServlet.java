package view;

import common.HtmlGenerator;
import dao.User;
import dao.UserDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        if(name==null||password==null||name.equals("")||password.equals("")) {
            //提交的数据有误
            String html= HtmlGenerator.getMessagePage("用户或密码为空","register.html");
            resp.getWriter().write(html);
            return;
        }
        UserDao userDao=new UserDao();
        if(userDao.select(name)!=null) {
            String html= HtmlGenerator.getMessagePage("用户名已经被注册，请重新输入","register.html");
            resp.getWriter().write(html);
            return;
        }
        User user=new User();
        user.setUsername(name);
        user.setPassword(password);
        userDao.insert(user);
        String html= HtmlGenerator.getMessagePage("注册成功点击跳转到登录页面","login.html");
        resp.getWriter().write(html);
    }
}
