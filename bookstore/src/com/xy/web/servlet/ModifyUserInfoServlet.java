package com.xy.web.servlet;

import com.xy.domain.User;
import com.xy.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModifyUserInfoServlet")
public class ModifyUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
            System.out.println(user);
            UserService userService=new UserService();
            userService.modifyUserInfo(user);
            response.sendRedirect(request.getContextPath()+"/modifyUserInfoSuccess.jsp");
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
