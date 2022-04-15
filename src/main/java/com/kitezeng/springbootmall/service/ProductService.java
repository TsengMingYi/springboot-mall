package com.kitezeng.springbootmall.service;

import com.kitezeng.springbootmall.dto.ProductRequest;
import com.kitezeng.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
