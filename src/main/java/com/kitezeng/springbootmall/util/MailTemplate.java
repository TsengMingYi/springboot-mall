package com.kitezeng.springbootmall.util;

public class MailTemplate {

    public static final int TEMPLATE_TEST = 1;

    public static String getText(int templateType,String message){
        switch (templateType){
            case 1:
                return "<html>\n" +
                        "<body style=\"background-color:PowderBlue;\">\n" +
                        "<p style=\"font-family:times;color:green;font-size:30px\">\n" +
                        message + "</p>\n" +
                        "</body>\n" +
                        "</html>";
            default:
                return message;
        }
    }
}
