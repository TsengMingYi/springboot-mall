package com.kitezeng.springbootmall.dao;

import com.kitezeng.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
