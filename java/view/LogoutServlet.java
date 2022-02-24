package view;

import dao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("text/html; charset=utf-8");
        HttpSession session=req.getSession(false);
        if(session==null) {
            String html="<h3>用户未登录</h3>";
            resp.getWriter().write(html);
            return;
        }
        session.removeAttribute("user");
        resp.sendRedirect("login.html");

    }
}
