package view;

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
@WebServlet("/blogDelete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户状态
        resp.setContentType("text/html;charset=utf-8");
        User user= util.checkLoginStatus(req);
        if(user==null) {
            String html="<h3>当前尚未登陆</h3>";
            resp.getWriter().write(html);
            return;
        }
        //获取当前请求的删除博客的id
        String blogId=req.getParameter("blogId");
        if(blogId==null||blogId.equals("")) {
            String html="<h3>blogId缺失</h3>";
            resp.getWriter().write(html);
            return;
        }
        BlogDao blogDao=new BlogDao();
        Blog blog=blogDao.selectOne(Integer.parseInt(blogId));
        if(blog.getUserId()!=user.getUserId()) {
            String html="<h3>不是作者不能删除</h3>";
            resp.getWriter().write(html);
            return;
        }
        //从数据库中进行删除

        blogDao.delete(Integer.parseInt(blogId));
        resp.sendRedirect("blog_list.html");

    }
}
