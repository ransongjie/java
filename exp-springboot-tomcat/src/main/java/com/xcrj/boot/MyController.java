package com.xcrj.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/test")
    public String test() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);
        return "success";
    }
}
