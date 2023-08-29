package com.xcrj.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/user/*")// 接受跨域的URL路径
                .allowedOrigins("http://localhost:8080") // 允许来源
//                .allowedOriginPatterns("*")// 允许所有来源
                .allowedMethods("GET", "POST", "DELETE", "PUT"); // 允许方法
    }
}
