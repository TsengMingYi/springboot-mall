package com.kitezeng.springbootmall.service.impl;

import com.kitezeng.springbootmall.dao.ProductDao;
import com.kitezeng.springbootmall.model.Product;
import com.kitezeng.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
