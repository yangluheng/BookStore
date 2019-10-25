package com.xy.web.servlet;

import com.xy.domain.PageResult;
import com.xy.domain.Product;
import com.xy.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowProductByPageServlet")
public class ShowProductByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category=request.getParameter("category");
        String pageStr=request.getParameter("page");
        int page=1;
        if (pageStr!=null&&!"".equals(pageStr)){
            page=Integer.parseInt(pageStr);
        }
        ProductService productService=new ProductService();
        PageResult<Product> pageResult= productService.findPageBooks(category,page);
        request.setAttribute("pageResult",pageResult);
        request.setAttribute("category",category);
        request.getRequestDispatcher("/product_list.jsp").forward(request,response);
    }
}
