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
import java.net.URLEncoder;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String checkcode_client=request.getParameter("checkcode");
        String checkcode_session= (String) request.getSession().getAttribute("checkcode_session");
        if (!checkcode_client.equals(checkcode_session)){
            request.setAttribute("checkcode_err","验证码不一样");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if (username==null){
            request.setAttribute("user_err","用户不存在");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if (password==null){
            request.setAttribute("password_err","用户名或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        UserService userService=new UserService();
        try {
            User user=userService.login(username,password);
            request.getSession().setAttribute("user",user);
            // 创建cookie并将成功登陆的用户保存在里面
            Cookie cookieUserName = new Cookie("username", URLEncoder.encode(username,"UTF-8"));
            Cookie cookiePassword = new Cookie("password", URLEncoder.encode(password,"UTF-8"));
            cookieUserName.setMaxAge(60*60*24*15); //十五天免登陆
            cookiePassword.setMaxAge(60*60*24*15);
            response.addCookie(cookieUserName); // 服务器返回给浏览器cookie以便下次判断
            response.addCookie(cookiePassword);
            if ("管理员".equals(user.getRole())){
                response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
            }
            else {
                /*request.getRequestDispatcher("/index.jsp").forward(request,response);*/
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("login_msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
