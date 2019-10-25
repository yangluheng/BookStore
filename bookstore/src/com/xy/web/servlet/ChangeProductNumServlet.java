package com.xy.web.servlet;

import com.xy.domain.Product;
import com.xy.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeProductNumServlet")
public class ChangeProductNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        int count= Integer.parseInt(request.getParameter("count"));
        ProductService productService=new ProductService();
        System.out.println("id="+id);
        System.out.println("count="+count);
        Product product=productService.findBook(id);
//        @SuppressWarnings("unchecked")
//        Map<Product,Integer> cart= new HashMap<Product,Integer>();
//        Map<Product,Integer> cart = null;
        System.out.println(product);
//        cart.put(product,count);
//        count++;
//        count++;
//        System.out.println(cart);
//        if (cart.containsKey(product)){
//            if (count==0){
//                cart.remove(product);
//            }
//            else {
//                cart.put(product,count);
//                count++;
//            }
//        }
//        request.getSession().setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath()+"/product_info.jsp");
    }
}
