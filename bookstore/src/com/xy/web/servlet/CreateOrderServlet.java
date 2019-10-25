package com.xy.web.servlet;

import com.xy.domain.Order;
import com.xy.domain.OrderItem;
import com.xy.domain.Product;
import com.xy.domain.User;
import com.xy.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 创建订单后台
 */
@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        Order order=new Order();
        System.out.println("后台");
        if (user==null){
            response.getWriter().write("非法访问");
            return;
        }
        Map<Product,Integer>cart= (Map<Product, Integer>) request.getSession().getAttribute("cart");
        if (cart==null||cart.size()==0){
            response.getWriter().write("购物车没有商品");
            return;
        }
        try {
            //1.1把请求参数参数封装成Order
            BeanUtils.populate(order,request.getParameterMap());
            order.setId(UUID.randomUUID().toString());
            //1.2补全Order数据
            order.setOrdertime(new Date());
            order.setUser(user);
            //1.3封装订单详情OrderItem
            List<OrderItem> items=new ArrayList<>();
            //取购物车
            double totalPrice=0;
            for (Map.Entry<Product,Integer> entry:cart.entrySet()
            ) {
                OrderItem item=new OrderItem();
                //设置购物数量
                item.setBuynum(entry.getValue());
                //设置商品
                item.setProduct(entry.getKey());
                //设置订单
                item.setOrder(order);
                totalPrice+=item.getProduct().getPrice()*item.getBuynum();
                items.add(item);
            }
            //设置Order中的item
            order.setItems(items);
            //1.4设置总价格
            order.setMoney(totalPrice);
            System.out.println("-------------------------");
            System.out.println("订单:");
            System.out.println(order);
            System.out.println("订单中的商品:");
            for (OrderItem item:items
            ) {
                System.out.println("商品名称:"+item.getProduct()+"\t数量:"+item.getBuynum());
            }
            //2.插入数据库
            OrderService orderService=new OrderService();
            orderService.createOrder(order);
            //3.订单成功(移除订单商品)
            request.getSession().removeAttribute("cart");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }
}
