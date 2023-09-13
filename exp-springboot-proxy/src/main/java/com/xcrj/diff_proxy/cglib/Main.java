package com.xcrj.diff_proxy.cglib;

public class Main {
    public static void main(String[] args) {
        EmailService emailService = (EmailService) EmailServiceProxyFactory.createProxy(EmailService.class);
        String result = emailService.send("java java java");
        System.out.println(result);
    }
}
