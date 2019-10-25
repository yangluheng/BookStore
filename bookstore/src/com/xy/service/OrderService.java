package com.xy.service;

import com.xy.dao.OrderDao;
import com.xy.dao.OrderItemDao;
import com.xy.dao.ProductDao;
import com.xy.domain.Order;
import com.xy.domain.OrderItem;
import com.xy.utils.ManagerThreadLocal;

import java.util.List;

public class OrderService {

    public void createOrder(Order order, List<OrderItem> items){

    }


    /**
     * 增加订单的业务方法
     * @param order
     */
    private OrderDao orderDao=new OrderDao();
    private OrderItemDao orderItemDao=new OrderItemDao();
    private ProductDao productDao=new ProductDao();
    public void createOrder(Order order){
        try {
            //开启事务
            ManagerThreadLocal.beginTransaction();
            //1.插入订单表
            orderDao.addOrder(order);
            //2.插入订单详情表
            orderItemDao.addItems(order.getItems());
            //3.减库存
            for (OrderItem item:order.getItems()
                 ) {
                productDao.updateStoreNum(item.getProduct().getId(),item.getBuynum());
            }
            //结束事务
            ManagerThreadLocal.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            ManagerThreadLocal.rollback();
        }
    }

    /**
     * 通过用户ID查询订单详情
     * @param userId
     * @return
     */
    public List<Order> findOrdersByUserId(String userId){
        try {
            return orderDao.findOrderByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过订单ID查询订单
     * @param orderId
     * @return
     */
    public Order findOrdersByOrderId(String orderId){
        try {
            return orderDao.findOrderByOrderId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
