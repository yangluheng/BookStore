package com.xy.dao;

import com.xy.domain.Order;
import com.xy.domain.OrderItem;
import com.xy.domain.Product;
import com.xy.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    public void addOrder(Order order) throws SQLException {
        String sql="INSERT INTO orders values(?,?,?,?,?,?,?,?)";
        List<Object> params=new ArrayList<>();
        params.add(order.getId());
        params.add(order.getMoney());
        params.add(order.getReceiverAddress());
        params.add(order.getReceiverName());
        params.add(order.getReceiverPhone());
        params.add(order.getPaystate());
        params.add(order.getOrdertime());
        params.add(order.getUser().getId());
        template.update(sql,order.getId(),order.getMoney(),order.getReceiverAddress(),order.getReceiverName(),order.getReceiverPhone(),order.getPaystate(),order.getOrdertime(),order.getUser().getId());
    }

    /**
     * 通过用户ID查找订单
     * @param userId
     * @return
     */
    public List<Order> findOrderByUserId(String userId){
        String sql="SELECT *FROM orders WHERE user_id=?";
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),userId);
    }

    /**
     * 通过订单ID查询Order数据
     * @param orderId
     * @return
     */
    public Order findOrderByOrderId(String orderId){
        //1.查询Order表，把数据封装到Order对象
        String sql1="SELECT *FROM orders WHERE id=?";
        Order order=template.queryForObject(sql1,new BeanPropertyRowMapper<Order>(Order.class),orderId);
        //2.查询OrderItem表，把数据封装到Order的items属性
        String sql2="SELECT o.*,p.name,p.price FROM orderitem o,products p WHERE o.product_id=p.id AND order_id=?";
        List<OrderItem> myItems=template.query(sql2, new ResultSetExtractor<List<OrderItem>>() {
            @Override
            public List<OrderItem> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                //1.创建集合对象
                List<OrderItem> items=new ArrayList<>();
                //2.遍历
                while (resultSet.next()){
                    OrderItem item=new OrderItem();
                    item.setBuynum(resultSet.getInt("buynum"));
                    Product product=new Product();
                    product.setId(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    item.setProduct(product);
                    items.add(item);
                }
                return items;
            }
        },orderId);
        order.setItems(myItems);
        return order;
    }

    public static void main(String[] args) {
        Order order=new OrderDao().findOrderByOrderId("ce96c7ef-225f-4bbe-9755-3545236c5f65");
        System.out.println(order);
        System.out.println("商品详情:");
        for (OrderItem item:order.getItems()){
            System.out.println("数量:"+item.getBuynum());
            System.out.println("名称:"+item.getProduct().getName());
            System.out.println("价格:"+item.getProduct().getPrice());
        }
        System.out.println();
    }
}
