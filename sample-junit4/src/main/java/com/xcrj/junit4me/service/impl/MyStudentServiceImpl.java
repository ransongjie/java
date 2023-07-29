package com.xcrj.junit4me.service.impl;

import org.springframework.stereotype.Service;
import com.xcrj.junit4me.service.MyStudentService;

@Service
public class MyStudentServiceImpl implements MyStudentService{

    @Override
    public String getName(){
        return "xcrj";
    }
}
