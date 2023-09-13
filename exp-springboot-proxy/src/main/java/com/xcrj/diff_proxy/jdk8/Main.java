package com.xcrj.diff_proxy.jdk8;

public class Main {
    public static void main(String[] args) {
        Object target = new EmailServiceImpl();
        EmailService emailService = (EmailService) EmailServiceProxyFactory.createProxy(target);
        String result = emailService.send("java java java");
        System.out.println(result);
    }
}
