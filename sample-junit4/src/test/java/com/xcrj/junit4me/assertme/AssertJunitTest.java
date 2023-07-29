package com.xcrj.junit4me.assertme;

import org.junit.Test;

//static import
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class AssertJunitTest {

    //相等测试
    @Test
    public void testMe1(){
        String expected="hi";
        String actual="hi";
        assertEquals(expected,actual);
    }

    //数组内容相等测试
    @Test
    public void testMe2(){
        String[] expected=new String[]{"hi","hi2"};
        String[] actual=new String[]{"hi","hi2"};
        assertArrayEquals(expected,actual);

        //null数组
        String[] expected1=null;
        String[] actual1=null;
        assertArrayEquals(expected1,actual1);

        //空数组
        String[] expected2=new String[0];
        String[] actual2=new String[0];
        assertArrayEquals(expected2,actual2);
    }

    //null判断
    @Test
    public void testMe3(){
        String expected=null;
        assertNull(expected);

        String expected1="";
        assertNotNull(expected1);
    }

    //引用判断
    @Test
    public void testMe4(){
        String expected="xcrj";
        String actual="xcrj";
        assertSame(expected,actual);

        String expected1="xcrj";
        String actual1="xcrj1";
        assertNotSame(expected1,actual1);
    }

    //true/false 判断
    @Test
    public void testMe5(){
        boolean expected=true;
        assertTrue(expected);

        boolean expected1=false;
        assertFalse(expected1);
    }

    //fail 测试主动失败
    @Test
    public void testMe6(){
        //将抛出 AssertionFailedError 异常
        fail("测试主动失败");
    }
}
