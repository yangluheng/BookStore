package com.xy.web.servlet;

import com.xy.domain.User;
import com.xy.exception.UserException;
import com.xy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        boolean flag = false;

        // 判断cookie是否有username，如果有代表登陆过
        Cookie[] cookies = request.getCookies();
        String username=null;
        String password=null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    username = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                    flag = true;
                }
                if ("password".equals(cookies[i].getName())) {
                    password = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
                System.out.println(username + "," + password);
                    UserService userService = new UserService();
                    User user = null;
                    try {
                        user = userService.login(username, password);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    request.getSession().setAttribute("user", user);
                }
        }
//            for (Cookie cookie : cookies) {
//                String username=URLDecoder.decode(cookie.getName(), "UTF-8");
//                String password=cookie.getValue();
//                System.out.println(username+","+password);
//                if (username.equals("username")) { // 表明已经登陆过了，就直接跳转了
//                    flag = true;
//                    UserService userService=new UserService();
//                    User user= null;
//                    try {
//                        user = userService.login(username,password);
//                    } catch (UserException e) {
//                        e.printStackTrace();
//                    }
//                    request.getSession().setAttribute("user",user);
//                }
//            }
//        }

        if(flag) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        }else {
            response.sendRedirect(request.getContextPath() + "/AutoLogin.jsp");
        }
    }
}
