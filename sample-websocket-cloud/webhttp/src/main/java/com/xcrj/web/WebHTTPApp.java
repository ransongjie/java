package com.xcrj.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WebHTTPApp {
    public static void main(String[] args) {
        SpringApplication.run(WebHTTPApp.class, args);
        System.out.println("WebHTTPApp 启动");
    }
}