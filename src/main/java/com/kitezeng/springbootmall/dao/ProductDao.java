package com.kitezeng.springbootmall.dao;

import com.kitezeng.springbootmall.constant.ProductCategory;
import com.kitezeng.springbootmall.dto.ProductRequest;
import com.kitezeng.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(String imageUrl,ProductRequest productRequest);

    void updateProduct(Integer productId , ProductRequest productRequest);


    void deleteProductById(Integer productId);

    void deleteProductFromName(String productName);

    void updateStock(Integer productId,Integer stock);
}
