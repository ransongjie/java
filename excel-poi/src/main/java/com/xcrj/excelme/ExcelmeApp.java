package com.xcrj.excelme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelmeApp {
    public static void main(String[] args) {
        SpringApplication.run(ExcelmeApp.class, args);
        System.out.println("ExcelmeApp 启动");
    }
}