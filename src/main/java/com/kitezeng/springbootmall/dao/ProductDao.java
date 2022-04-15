package com.kitezeng.springbootmall.dao;

import com.kitezeng.springbootmall.dto.ProductRequest;
import com.kitezeng.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
