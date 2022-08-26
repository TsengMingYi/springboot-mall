package com.kitezeng.springbootmall.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMimeMail(String from,String to,String subject,String message,int template,String attachPath) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //通过MimeMessageHelper进行邮件内容的设置
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        //发送html内容
        mimeMessageHelper.setText(MailTemplate.getText(MailTemplate.TEMPLATE_TEST,message),true);
        //发送附件
        File testAttach = new File(attachPath);
        if (testAttach.exists()){
            mimeMessageHelper.addAttachment("ball1.jpg",testAttach);
        }
        //发送邮件
        javaMailSender.send(mimeMessage);
    }
}
