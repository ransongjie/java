package com.xcrj.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketApp{
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApp.class, args);
        System.out.println("WebSocketApp 启动");
    }
}