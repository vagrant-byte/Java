package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.User;
import dao.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("test/html,charset=utf8");
        req.setCharacterEncoding("utf8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(username==null||password==null||username.equals("")||password.equals("")) {
            String html="<h3>登录失败！用户名或密码错误<h3>";
            resp.getWriter().write(html);
            return;
        }
        UserDao userDao=new UserDao();
        User user=userDao.select(username);
        if(user==null) {
            String html="<h3>登录失败！用户名或密码错误<h3>";
            resp.getWriter().write(html);
            return;
        }
        if(!user.getPassword().equals(password)) {
            String html="<h3>登录失败！用户名或密码错误<h3>";
            resp.getWriter().write(html);
            return;
        }
       HttpSession session=req.getSession(true);
        session.setAttribute("username",username);
        resp.sendRedirect("index.html");
    }
}
