package com.kitezeng.springbootmall.service;

import com.kitezeng.springbootmall.dao.ProductQueryParams;
import com.kitezeng.springbootmall.dto.ProductRequest;
import com.kitezeng.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId , ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
