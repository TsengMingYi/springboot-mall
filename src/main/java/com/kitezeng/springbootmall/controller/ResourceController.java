package com.kitezeng.springbootmall.controller;

import com.kitezeng.springbootmall.dao.ProductDao;
import com.kitezeng.springbootmall.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ResourceController {

    @Autowired
    private FileService fileService;


//    @PostMapping("/upload")
//    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
//
////        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
//        return fileService.upload(multipartFile);
//    }

//    @PostMapping("/upload/{fileName}")
//    public Object download(@PathVariable String fileName) throws IOException {
////        logger.info("HIT -/download | File Name : {}", fileName);
//        return fileService.download(fileName);
//    }
}
