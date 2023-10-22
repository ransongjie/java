package com.xcrj.test;

/**
 * 字符串常量折叠
 */
public class Test4a {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        // 字符串常量拼接
        String str = "xc" + "rj";
        // 字符串常量拼接
        final String str1 = "xc";
        final String str2 = "rj";
        // javac 常量折叠
        String str3 = str1 + str2;
        System.out.println(str == str3);// true
        // 字符串变量拼接
        String str4 = "xc";
        // 存在1个字符串变量，使用 StringBuilder 拼接字符串
        String str5 = str + "rj";
        System.out.println(str == str5);// false
    }
}
