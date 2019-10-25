package com.xy.web.servlet;

import com.xy.domain.Product;
import com.xy.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProductInfoServlet")
public class ProductInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        int count=1;
        ProductService productService=new ProductService();
        Product product=productService.findBook(id);
        request.setAttribute("product",product);
        request.setAttribute("count",count);
        request.getRequestDispatcher("/product_info.jsp").forward(request,response);
    }
}
