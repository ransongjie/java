package com.xcrj.junit4me.assertme;

import org.junit.Test;

//运行时间单元测试，timeout is deadline
public class AssertTimeoutTest {
    //注意单位是 ms
    @Test(timeout=100)
    public void testEx1() {
        while(true){}
    }
}
