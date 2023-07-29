package com.xcrj.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xcrj")
public class StudentController {

    @GetMapping("/name")
    public String xcrj(){
        return "xcrj";
    }
}