package com.xy.dao;

import com.xy.domain.Product;
import com.xy.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    /***
     *
     * @param category
     * @return
     */
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public long count(String category){
        long count=0;
        String sql="SELECT COUNT(*) FROM products WHERE 1=1";
        if (category!=null&&!"".equals(category)){
            sql+=" AND category=?";
            count= (long) template.queryForObject(sql,Integer.class,category);
        }else {
            /*count= (long) template.query(sql,new BeanPropertyRowMapper<Product>(Product.class));*/
            count= template.queryForObject(sql,Integer.class);
        }
        return count;
    }

    /**
     *
     * @param category
     * @param page
     * @param pageSize
     * @return
     */
    public List<Product> findBooks(String category, int page,int pageSize){
        String sql="SELECT *FROM products WHERE 1=1";
        List<Object> prams=new ArrayList<>();
        if (category!=null&&!"".equals(category)){
            sql+=" AND category=?";
            prams.add(category);
        }
        int start=(page-1)*pageSize;
        int length=pageSize;
        sql+=" LIMIT ?,?";
        prams.add(start);
        prams.add(length);
        return  template.query(sql,new BeanPropertyRowMapper<Product>(Product.class),prams.toArray());
    }

    /**
     * 通过id查找商品
     * @param id
     * @return
     */
    public Product findBook(String id){
        String sql="SELECT *FROM products WHERE id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Product>(Product.class),id);
    }

    /**
     * 更新库存
     * @param productId
     * @param num
     */
    public void updateStoreNum(int productId,int num) throws SQLException {
        String sql="UPDATE products SET pnum=pnum- ? WHERE id=?";
        template.update(sql,num,productId);
    }
    public static void main(String[] args) {
        ProductDao productDao=new ProductDao();

        String category="计算机";
        long count=productDao.count(category);
        List<Product> books=productDao.findBooks(category,1,4);
        for (Product p:books
             ) {
            System.out.println(p);
        }
        System.out.println(count);
    }
}
