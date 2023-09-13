package com.xcrj.diff_proxy.jdk8;

public class EmailServiceImpl implements EmailService {

    @Override
    public String send(String message) {
        System.out.println("send email: " + message);
        return "success";
    }
}
