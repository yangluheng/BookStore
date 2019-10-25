package com.xy.web.servlet;

import com.xy.domain.Order;
import com.xy.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 查询订单详情
 */
@WebServlet("/FindOrderByOrderIdServlet")
public class FindOrderByOrderIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        OrderService orderService=new OrderService();
        Order order=orderService.findOrdersByOrderId(orderId);
        request.setAttribute("order",order);
        request.getRequestDispatcher("/orderInfo.jsp").forward(request,response);
    }
}
