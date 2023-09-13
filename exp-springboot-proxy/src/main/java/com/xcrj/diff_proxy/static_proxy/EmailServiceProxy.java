package com.xcrj.diff_proxy.static_proxy;

public class EmailServiceProxy implements EmailService {
    private final EmailService emailService;//final

    EmailServiceProxy(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public String send(String message) {
        //调用方法之前，增强功能
        System.out.println("调用方法之前，增强功能");
        //调用目标类方法
        String result=this.emailService.send(message);
        //调用方法之后，增强功能
        System.out.println("调用方法之后，增强功能");
        return result;
    }
}
