package view;

import common.util;
import dao.Blog;
import dao.BlogDao;
import dao.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/blog_list.html")
public class BlogLIstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        User user=util.checkLoginStatus(req);
        if(user==null) {
            resp.sendRedirect("login.html");
        }
        //1.从数据库中拿到博客列表
        BlogDao blogDao=new BlogDao();
        List<Blog> blogs=blogDao.selectAll();
        //2.通过模板引擎，渲染页面
        ServletContext context=this.getServletContext();
        TemplateEngine engine= (TemplateEngine) context.getAttribute("engine");
        WebContext webContext=new WebContext(req,resp,context);
        webContext.setVariable("blogs",blogs);
        webContext.setVariable("user",user);
        String html=engine.process("blog_list",webContext);
        resp.getWriter().write(html);
    }
}
