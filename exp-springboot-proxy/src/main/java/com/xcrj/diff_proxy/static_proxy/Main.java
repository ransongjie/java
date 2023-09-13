package com.xcrj.diff_proxy.static_proxy;

public class Main {
    public static void main(String[] args) {
        EmailService emailService = new EmailServiceImpl();
        EmailServiceProxy emailServiceProxy = new EmailServiceProxy(emailService);
        String result = emailServiceProxy.send("java golang c++");
        System.out.println(result);
    }
}
