package com.kitezeng.springbootmall.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public Object upload(MultipartFile multipartFile);
    public Object download(String fileName) throws IOException;
}
