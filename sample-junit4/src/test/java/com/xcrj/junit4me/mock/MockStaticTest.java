package com.xcrj.junit4me.mock;

import com.xcrj.junit4me.utils.MyUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

//mock 静态方法
public class MockStaticTest {

    //静态无参方法 mock
    @Test
    public void testGetStaticNameWithoutParam(){
        assertEquals("xcrj", MyUtils.getStaticNameWithoutParam());

        //To make sure a static mock remains temporary, it is recommended to define the scope within a try-with-resources construct.
        try (MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            //当调用 静态方法时，返回xcrj22
            mockedStatic.when(MyUtils::getStaticNameWithoutParam).thenReturn("xcrj22");

            //断言 静态方法调用
            assertEquals("xcrj22", MyUtils.getStaticNameWithoutParam());

            //verify(mock, times(1)).someMethod("some arg");
            //验证静态方法是否只被调用1次
            mockedStatic.verify(MyUtils::getStaticNameWithoutParam);
        }

        //staticMock 的作用域只在try块中
        assertEquals("xcrj", MyUtils.getStaticNameWithoutParam());
    }

    //静态有参方法 mock
    @Test
    public void testGetStaticNameWithParam(){
        assertEquals("xcrj", MyUtils.getStaticNameWithParam("xcrj"));

        //To make sure a static mock remains temporary, it is recommended to define the scope within a try-with-resources construct.
        try (MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            //当调用 静态方法时，返回xcrj22
            mockedStatic.when(()->MyUtils.getStaticNameWithParam(anyString())).thenReturn("xcrj22");

            //断言 静态方法调用
            assertEquals("xcrj22", MyUtils.getStaticNameWithParam(anyString()));

            //verify(mock, times(1)).someMethod("some arg");
            //验证静态方法是否只被调用1次
            mockedStatic.verify(()->MyUtils.getStaticNameWithParam(anyString()));
        }

        //staticMock 的作用域只在try块中
        assertEquals("xcrj", MyUtils.getStaticNameWithParam("xcrj"));
    }
}
