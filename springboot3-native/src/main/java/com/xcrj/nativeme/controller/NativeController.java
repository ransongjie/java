package com.xcrj.nativeme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/native")
public class NativeController {
    @GetMapping("/xcrj")
    public String xcrj(){
        return "success";
    }
}
