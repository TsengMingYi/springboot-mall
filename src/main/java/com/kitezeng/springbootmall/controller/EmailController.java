package com.kitezeng.springbootmall.controller;

import com.kitezeng.springbootmall.model.Email;
import com.kitezeng.springbootmall.util.CreateVerifiCodeImage;
import com.kitezeng.springbootmall.util.MailTemplate;
import com.kitezeng.springbootmall.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;

@CrossOrigin
@RestController
public class EmailController {

    private String attachPath = "/Users/zengmingyi/IdeaProjects/springboot-mall/src/main/resources/ball1.png";

    @Autowired
    private MailUtils mailUtils;

    @PostMapping("/sendmail")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid Email email, HttpServletRequest request) throws MessagingException {

        BufferedImage bufferedImage = CreateVerifiCodeImage.getVerifiCodeImage();

        //獲取驗證碼
        String verifiCode = String.valueOf(CreateVerifiCodeImage.getVerifiCode());

        //验证码存入session
        request.getSession().setAttribute("verifiCode",verifiCode);

        mailUtils.sendMimeMail("tsengmingyi1@gmail.com",email.getEmail(),"NO Idea 驗證碼:","驗證碼是："+verifiCode, MailTemplate.TEMPLATE_TEST,attachPath);
//        SimpleMailMessage message = new SimpleMailMessage();
//        //邮箱主题
//        message.setSubject("NO Idea 驗證碼:");
//        //邮箱内容
//        message.setText("驗證碼是："+verifiCode);
//        //发给谁（email是前端异步提交过来的数据）
//        message.setTo(email);
//        //谁发的
//        message.setFrom("tsengmingyi1@gmail.com");
//        //发送邮件
//        javaMailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(email);
    }
}
