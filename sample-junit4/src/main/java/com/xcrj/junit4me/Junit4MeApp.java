package com.xcrj.junit4me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Junit4MeApp {
    public static void main(String[] args) {
        SpringApplication.run(Junit4MeApp.class, args);
        System.out.println("Junit4MeApp 启动");
    }
}