package com.xcrj.chaos;

import java.sql.Date;

public class Main2 {
    public static void main(String[] args) {
        test01();
    }

    /**
     *
     */
    private static void test01() {
        byte a = 127;//二进制补码 01111111 127
        a = (byte) (a + 1);//二进制补码 10000000 -128
        System.out.println(a);//-128
        short b = 1;
        b += 1;
        System.out.println(b);//2
    }

    /**
     * null不属于任何类型，可以被转换成任何类型，
     * 但是用instanceof永远返回false.
     */
    private static void test02() {
        ((Main2) null).f02();
    }

    private static void f02() {
        System.out.println("hello");
    }
}
