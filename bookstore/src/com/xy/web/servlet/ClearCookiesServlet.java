package com.xy.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/ClearCookiesServlet")
public class ClearCookiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这里就是把username的cookie设置成0秒有效期，就是直接删除掉了
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(URLDecoder.decode(cookie.getName(), "utf-8"));
                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) { // 表明已经登陆过了，就直接跳转了
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
