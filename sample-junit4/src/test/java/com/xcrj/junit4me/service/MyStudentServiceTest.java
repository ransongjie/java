package com.xcrj.junit4me.service;

import javax.annotation.Resource;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

//单元测试 MyStudentService
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyStudentServiceTest {
    @Resource
    private MyStudentService myStudentService;

    @Test
    public void testGetName() {
        Assert.assertEquals(myStudentService.getName(), "xcrj");
    }
}
