package com.xy.web.servlet;

import com.xy.domain.Product;
import com.xy.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/ChangeNumServlet")
public class ChangeNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String num=request.getParameter("num");
        ProductService productService=new ProductService();
        System.out.println("id="+id);
        System.out.println("num="+num);
        Product product=productService.findBook(id);
        @SuppressWarnings("unchecked")
        Map<Product,Integer> cart= (Map<Product, Integer>) request.getSession().getAttribute("cart");
        if (cart.containsKey(product)){
            if (num.equals("0")){
                cart.remove(product);
            }
            else {
                cart.put(product,Integer.parseInt(num));
            }
        }
        request.getSession().setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath()+"/cart.jsp");
    }
}
