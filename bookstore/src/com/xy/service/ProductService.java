package com.xy.service;

import com.xy.dao.ProductDao;
import com.xy.domain.PageResult;
import com.xy.domain.Product;

import java.util.List;

public class ProductService {
    ProductDao productDao=new ProductDao();
    public PageResult<Product> findPageBooks(String category,int page){

        PageResult<Product> pageResult= null;
        try {
            pageResult = new PageResult<>();
            long totalCount=productDao.count(category);
            pageResult.setTotalCount(totalCount);
            int totalPage= (int) Math.ceil(totalCount*1.0/pageResult.getPageSize());
            pageResult.setTotalPage(totalPage);
            pageResult.setCurrentPage(page);        //设置当前页数
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Product> list=productDao.findBooks(category,page,pageResult.getPageSize());            //提前在封装类中设置初值为4
/*
        List<Product> list=productDao.findBooks(category,page,4);    //没有设置pageResult的每页显示条数页面值
*/


        pageResult.setList(list);
        return pageResult;
    }

    public Product findBook(String id){
        try {
            return productDao.findBook(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public static void main(String[] args) {
        ProductDao productDao=new ProductDao();
        ProductService productService=new ProductService();
        String category="计算机";
        PageResult pageResult= productService.findPageBooks(category,2);
        System.out.println(pageResult.getList());

        System.out.println(pageResult);
    }
}
