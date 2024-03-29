package com.example.ontheway.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//从参数中获取cookie
public class CookieUtil {
    public static String getValue(HttpServletRequest request,String name) throws IllegalAccessException {
        if(request==null||name==null) {
            throw new IllegalAccessException("参数为空！");
        }
        Cookie[] cookies=request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


}
