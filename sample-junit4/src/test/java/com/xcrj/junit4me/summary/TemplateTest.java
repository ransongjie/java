package com.xcrj.junit4me.summary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//单元测试模板
public class TemplateTest {

    //类中所有@Test方法之前执行, 注意必须修饰static方法
    @BeforeClass
    public static void testBeforeClass(){
        System.out.println("static beforeClass");
    }

    //类中所有@Test方法之后执行，注意必须修饰static方法
    @AfterClass
    public static void testAfterClass(){
        System.out.println("static afterClass");
    }

    //每个@Test方法之前执行
    @Before
    public void testBefore(){
        System.out.println("before");
    }

    //每个@Test方法之后执行
    @After
    public void testAfter(){
        System.out.println("after");
    }

    @Test
    public void testHi1(){
        System.out.println("testHi1");
    }

    @Test
    public void testHi2(){
        System.out.println("testHi2");
    }
}
