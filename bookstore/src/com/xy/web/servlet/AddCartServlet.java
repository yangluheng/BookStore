package com.xy.web.servlet;

import com.xy.domain.Product;
import com.xy.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id=request.getParameter("id");
        String count=request.getParameter("count");
        System.out.println(count);
        ProductService productService=new ProductService();
        System.out.println(id);
        Product product=productService.findBook(id);
        Map<Product,Integer> cart=(Map<Product,Integer>)request.getSession().getAttribute("cart");
        if (cart==null){
            cart=new HashMap<Product,Integer>();
            if ("count".equals("0")){
                cart.put(product,0);
                response.sendRedirect(request.getContextPath() + "/addOrdersSuccess.jsp");
                return;
            }
            cart.put(product,Integer.parseInt(count));
//            cart.put(product,1);
        }
        else {
            if (cart.containsKey(product)){
                if ("count".equals("0")){
//                    cart.put(product,cart.get(product));
                    response.sendRedirect(request.getContextPath() + "/addOrdersSuccess.jsp");
                    return;
                }
                cart.put(product,cart.get(product)+Integer.parseInt(count));
//                cart.put(product,cart.get(product)+1);
            }
            else {
                if ("count".equals("0")){
//                    cart.put(product,cart.get(product));
                    response.sendRedirect(request.getContextPath() + "/addOrdersSuccess.jsp");
                    return;
                }
                cart.put(product,Integer.parseInt(count));
//                cart.put(product,1);
            }
        }
        for (Map.Entry<Product,Integer> entry:cart.entrySet()
             ) {
            System.out.println(entry.getKey()+"数量"+entry.getValue());
        }
        request.getSession().setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath() + "/addOrdersSuccess.jsp");
//        String a1="<a href=\""+request.getContextPath()+"/ShowProductByPageServlet\">继续购物</a>";
//        String a2="&nbsp;&nbsp<a href=\""+request.getContextPath()+"/cart.jsp\">查看购物车</a>";
//        response.getWriter().write(a1);
//        response.getWriter().write(a2);
    }
}
