package view;

import common.util;
import dao.Blog;
import dao.BlogDao;
import dao.User;
import dao.UserDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/blog_detaill.html")
public class BlogDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        User user=util.checkLoginStatus(req);
        if(user==null) {
            resp.sendRedirect("login.html");
        }
        //1.获取blogId参数
        String blogId=req.getParameter("blogId");
        if(blogId==null||"".equals(blogId)) {
            String html="<h3>blogId字段缺失</h3>";
            resp.getWriter().write(html);
            return;
        }
        //2.根据blogId从数据库中查询
        BlogDao blogDao=new BlogDao();
        Blog blog=blogDao.selectOne(Integer.parseInt(blogId));
        if(blog==null) {
            String html="<h3>当前博客不存在</h3>";
            resp.getWriter().write(html);
            return;
        }
        UserDao userDao=new UserDao();
        User author=userDao.selectById(blog.getUserId());
        //3.页面渲染
        ServletContext context=this.getServletContext();
        TemplateEngine engine= (TemplateEngine) context.getAttribute("engine");
        WebContext webContext=new WebContext(req,resp,context);
        webContext.setVariable("blog",blog);
        webContext.setVariable("user",author);
        webContext.setVariable("showDeleteBtn",blog.getUserId()==user.getUserId());
        String html=engine.process("blog_detaill",webContext);
        resp.getWriter().write(html);

    }
}
