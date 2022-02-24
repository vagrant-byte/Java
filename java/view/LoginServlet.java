package view;

import dao.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        //1.从请求中读取用户名和密码
        String username=req.getParameter("username");
        System.out.println(username);
        String password=req.getParameter("password");
        System.out.println(password);
        if(username==null||password==null||"".equals(username)||"".equals(password)) {
            String html="<h3>用户名或密码错误1</h3>";
            resp.getWriter().write(html);
            return;
        }
        //2.从数据库中根据用户名查找密码
        UserDao userDao=new UserDao();
        User user=userDao.select(username);
        if(user==null) {
            String html="<h3>用户名或密码错误2</h3>";
            resp.getWriter().write(html);
            return;
        }
        //3.把数据库中查询的密码和用户输入密码进行对比
        if(!user.getPassword().equals(password)) {
            String html="<h3>用户名或密码错误3</h3>";
            resp.getWriter().write(html);
            return;
        }
        //4.登录成功，把当前的user对象存到httpSession中
        HttpSession session=req.getSession(true);
        session.setAttribute("user",user);
        //5.登录成功，重定向到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}
