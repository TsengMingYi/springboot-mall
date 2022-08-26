package com.kitezeng.springbootmall.controller;

import cn.hutool.core.io.FileUtil;
import com.kitezeng.springbootmall.service.PythonService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PythonController {

    @Autowired
    private PythonService pythonService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello()  {
//    public ResponseEntity<byte[]> hello()  {
        String string = pythonService.usePython();
//        String pyFilePath = "src/main/resources/pyfiles/my_plot.png";
//        String pyFilePath = pythonService.getFilesPath("pyfiles/my_plot.png","xx_xxxx.png");
        //String pyFilePath = String.valueOf(classPathResource.getURL());
        //String pyFilePath = "/Users/zengmingyi/IdeaProjects/springboot-mall/src/main/resources/my_plot.png";

        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource("my_plot.png").getInputStream();
            String s = new ClassPathResource("my_plot.png").getURI().toString().substring(5);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.status(HttpStatus.OK).body(string);
        //第一个参数，就是图片输入流转的byte[]，第二个参数就是前面定义的hettpHeaders，第三个就是在什么情况下返回，我这里就是在请求ok的情况下
//        return new ResponseEntity<>(pythonService.getBytesByStream(inputStream),headers, HttpStatus.OK);
    }

//    @GetMapping("/hello1")
//    public void getAvatar(HttpServletResponse response) throws IOException {
//        pythonService.usePython();
//        ServletOutputStream outputStream = null;
//        InputStream inputStream = null;
//
//        try {
//            String imgPath = "/Users/zengmingyi/PycharmProjects/hello/my_plot.png";
//            if (StringUtils.isEmpty(imgPath)) {
//                ClassPathResource classPathResource = new ClassPathResource("/static/admin/img/logo.png");
//                inputStream = classPathResource.getInputStream();
//            } else {
//                inputStream = FileUtil.getInputStream(imgPath);
//            }
//            response.setContentType("image/png");
//            outputStream = response.getOutputStream();
//
//            int len = 0;
//            byte[] buffer = new byte[4096];
//            while ((len = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, len);
//            }
//            outputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            outputStream.close();
//            inputStream.close();
//        }
//    }
}
