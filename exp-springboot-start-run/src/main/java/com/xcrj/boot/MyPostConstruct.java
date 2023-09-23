package com.xcrj.boot;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Component
public class MyPostConstruct {
    @PostConstruct
    public void testPostConstruct() {
        System.out.println("@PostConstruct");
    }
}
