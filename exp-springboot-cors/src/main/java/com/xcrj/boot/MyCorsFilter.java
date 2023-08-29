package com.xcrj.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration
public class MyCorsFilter {
//    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");// 支持所有域
        corsConfiguration.addAllowedMethod("*");// 支持所有方法
        corsConfiguration.setAllowCredentials(true);// 发送cookie
        // 所有路径
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        // 返回corsFilter对象
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
