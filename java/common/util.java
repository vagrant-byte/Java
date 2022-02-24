package common;

import dao.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class util {
    public static User checkLoginStatus(HttpServletRequest req) {
        HttpSession session=req.getSession(false);
        if(session==null) {
            //未登录
            return null;
        }
        User user= (User) session.getAttribute("user");
        if(user==null) {
            return null;
        }
        return user;
    }
}
