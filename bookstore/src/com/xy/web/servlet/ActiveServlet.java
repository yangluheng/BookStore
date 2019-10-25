package com.xy.web.servlet;

import com.xy.exception.UserException;
import com.xy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ActiveServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String activecode = request.getParameter("activeCode");
        UserService userService=new UserService();
        try {
            userService.activeUser(activecode);
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
