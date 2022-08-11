package com.kitezeng.springbootmall.service;

import com.kitezeng.springbootmall.dao.ProductQueryParams;
import com.kitezeng.springbootmall.dto.ProductRequest;
import com.kitezeng.springbootmall.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public Integer countProduct(ProductQueryParams productQueryParams);

    public List<Product> getProducts(ProductQueryParams productQueryParams);

    public Product getProductById(Integer productId);

    public Integer createProduct(String imageUrl,ProductRequest productRequest);

    public void updateProduct(Integer productId , ProductRequest productRequest);


    public void deleteProductById(Integer productId);

    public void deleteProductFromName(String productName);

//    public String saveTest(MultipartFile file) throws IOException;

    public Object upload(MultipartFile multipartFile);

    public Object download(String fileName) throws IOException;

}
