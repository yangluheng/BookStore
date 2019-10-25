package com.xy.web.servlet;

import com.xy.domain.User;
import com.xy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("id");
        User user=null;
        UserService userService=new UserService();
        try {
            user=userService.findUserById(userId);
            request.setAttribute("modifyUser",user);
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
