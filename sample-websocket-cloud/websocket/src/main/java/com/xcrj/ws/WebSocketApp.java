package com.xcrj.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WebSocketApp{
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApp.class, args);
        System.out.println("WebSocketApp 启动");
    }
}