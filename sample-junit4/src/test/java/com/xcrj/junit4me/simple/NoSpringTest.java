package com.xcrj.junit4me.simple;
import org.junit.Test;

import com.xcrj.junit4me.model.SimpleStudent;

import org.junit.Assert;

//简单单元测试，不依赖 spring 环境
public class NoSpringTest {
    @Test
    public void testSayHi1(){
        SimpleStudent simpleStudent=new SimpleStudent();
        Assert.assertEquals(simpleStudent.sayHi(),"hi");
    }
}
