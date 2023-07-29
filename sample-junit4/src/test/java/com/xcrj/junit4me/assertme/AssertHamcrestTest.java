package com.xcrj.junit4me.assertme;

import org.junit.Test;

//Matcher
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;

public class AssertHamcrestTest {

    //值相等
    @Test
    public void testMe7(){
        // Integer expected=new Integer(10000);
        // Integer actual=new Integer(10000);

        //使用 static facroty 方法 不使用构造方法
        Integer expected=Integer.valueOf(10000);
        Integer actual=Integer.valueOf(10000);

        assertThat(actual,is(expected));
    }

    //类实例
    @Test
    public void testMe8(){
        String actual="hi";
        assertThat(actual,isA(String.class));
    }

    //值不相等
    @Test
    public void testMe9(){
        // Integer expected=new Integer(10000);
        // Integer actual=new Integer(20000);

        Integer expected=Integer.valueOf(10000);
        Integer actual=Integer.valueOf(20000);
        assertThat(actual,is(not(expected)));
    }
}
