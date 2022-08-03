package com.kitezeng.springbootmall.controller;

import com.kitezeng.springbootmall.constant.ProductCategory;
import com.kitezeng.springbootmall.dao.ProductQueryParams;
import com.kitezeng.springbootmall.dto.ProductRequest;
import com.kitezeng.springbootmall.model.Product;
import com.kitezeng.springbootmall.service.ProductService;
import com.kitezeng.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //查詢條件 filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        // 取得 product List
        List<Product> productList = productService.getProducts(productQueryParams);


        // 取得 product 總數
        Integer total = productService.countProduct(productQueryParams);

        // 分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    @PostMapping("/products")
//    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
//        Integer productId = productService.createProduct(productRequest);
//
//        Product product = productService.getProductById(productId);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(product);
//    }
@PostMapping("/products")
public ResponseEntity<Product> createProduct(@RequestParam("file") MultipartFile multipartFile,@RequestBody @Valid ProductRequest productRequest) {
        String imageUrl = (String) productService.upload(multipartFile);
    Integer productId = productService.createProduct(imageUrl,productRequest);

    Product product = productService.getProductById(productId);

    return ResponseEntity.status(HttpStatus.CREATED).body(product);
}

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        Product product = productService.getProductById(productId);

        if (product == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

//    @DeleteMapping("/products/{productId}")
//    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
//        productService.deleteProductById(productId);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

    @DeleteMapping("/products/{productName}")
    public ResponseEntity<?> deleteProductFromName(@PathVariable String productName){
        productService.deleteProductFromName(productName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @PostMapping("/upload")
//    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
//        String imageUrl = (String)productService.upload(multipartFile);
//
////        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
//        return productService.upload(multipartFile);
//    }
//
//    @PostMapping("/upload/{fileName}")
//    public Object download(@PathVariable String fileName) throws IOException {
////        logger.info("HIT -/download | File Name : {}", fileName);
//        return productService.download(fileName);
//    }

}
