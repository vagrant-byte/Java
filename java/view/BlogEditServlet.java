package view;

import com.mysql.cj.Session;
import common.util;
import dao.Blog;
import dao.BlogDao;
import dao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/blog_edit")
public class BlogEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text.html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        User user= util.checkLoginStatus(req);
        if(user==null) {
            String html="<h3>当前未登录</h3>";
            resp.getWriter().write(html);
            return;
        }
        //读取请求中的参数
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        if(title==null||"".equals(title)||content==null||"".equals(content)) {
            String html="<h3>提交的title或content不存在</h3>";
            resp.getWriter().write(html);
            return;
        }
        //插入播客
        BlogDao blogDao=new BlogDao();
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blogDao.insert(blog);
        resp.sendRedirect("blog_list.html");
    }
}
