package com.xcrj.diff_proxy.cglib;

public class EmailService {
    public String send(String message) {
        System.out.println("send email: " + message);
        return "success";
    }
}
