package com.xcrj.junit4me.assertme;
import org.junit.Test;

//异常单元测试，期待抛出的异常
public class AssertExceptionTest {

    @Test(expected= ArithmeticException.class)
    public void testEx1() {
        int a=10/0;
    }
}
