package com.xcrj.test;

import java.util.Date;

public class Test extends Date {
    public static void main(String[] args) {
        Test t = new Test();
        t.test();
        t.test1();
    }
    public void test() {
        //com.xcrj.test.Test
        System.out.println(super.getClass().getName());
    }
    public void test1() {
        //java.util.Date
        System.out.println(this.getClass().getSuperclass().getName());
    }
}
