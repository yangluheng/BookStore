package com.xy.dao;

import com.xy.domain.OrderItem;
import com.xy.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 增加订单详情
     * @param items
     */
    public void addItems(List<OrderItem> items) throws SQLException {
        String sql="INSERT INTO orderitem(order_id,product_id,buynum) VALUES(?,?,?)";
        for (OrderItem item:items
             ) {
            template.update(sql,item.getOrder().getId(),item.getProduct().getId(),item.getBuynum());
        }
    }



}
