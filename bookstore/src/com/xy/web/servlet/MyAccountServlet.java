package com.xy.web.servlet;

import com.xy.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MyAccountServlet")
public class MyAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        if (user!=null){
            response.sendRedirect(request.getContextPath()+"/myAccount.jsp");
        }else {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }
}
