package com.xcrj.diff_proxy.static_proxy;

public class EmailServiceImpl implements EmailService{

    @Override
    public String send(String message) {
        System.out.println("send email: " + message);
        return "success";
    }
}
