package com.xy.web.servlet;

import com.xy.domain.Order;
import com.xy.domain.User;
import com.xy.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/***
 * 查询订单，通过用户ID查询
 */
@WebServlet("/FindOrderByIdServlet")
public class FindOrderByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户ID
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            response.getWriter().write("非法访问...");
            return;
        }
        //2.查询数据，调用Service
        OrderService orderService=new OrderService();
        List<Order> orders=orderService.findOrdersByUserId(user.getId()+"");
        //3.存数据到request
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/orderlist.jsp").forward(request,response);
    }
}
