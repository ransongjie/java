package com.xcrj.test;

/**
 * 测试 String intern() 方法
 */
public class Test4 {
    public static void main(String[] args) {
        test0();
        test1();
        test2();
        test3();
        test4();
    }

    public static void test0() {
        //str1=堆区中的引用
        String str1 = new String("xcrj");
        //str1a=字符串常量池中的引用
        String str1a = str1.intern();
        //str2=字符串常量池中的引用
        String str2 = "xcrj";
        System.out.println(str1 == str2);// false
        System.out.println(str1a == str2);// true
    }

    public static void test1() {
        final String stra = "xcrj1";
        final String strb = "xcrj1";
        // javac 常量折叠为"xcrj1xcrj1"，并经过 StringBuilder 拼接字符串
        String str1 = stra + strb;
        String str1a = str1.intern();
        String str2 = "xcrj1xcrj1";
        System.out.println(str1 == str2);// true
        System.out.println(str1a == str2);// true
    }

    public static void test2() {
        // javac 常量折叠为"xcrj2xcrj2"，并经过 StringBuilder 拼接字符串
        String str1 = "xcrj2" + "xcrj2";
        String str1a = str1.intern();
        String str2 = "xcrj2xcrj2";
        System.out.println(str1 == str2);// true
        System.out.println(str1a == str2);// true
    }

    public static void test3() {
        // 至少含有1个字符串变量，才会使用 StringBuilder 进行拼接
        String str1 = new String("xcrj3") + new String("xcrj3");
        String str1a = str1.intern();
        String str2 = "xcrj3xcrj3";
        System.out.println(str1 == str2);// true
        System.out.println(str1a == str2);// true
    }

    public static void test4() {
        // str1=堆区中引用
        String str1 = new String("xcrj4") + "xcrj4";
        // str1a=堆区中引用被纳入到字符串常量池中，对StringBuilder拼接字符串的优化
        String str1a = str1.intern();
        String str2 = "xcrj4xcrj4";
        System.out.println(str1 == str2);// true
        System.out.println(str1a == str2);// true
    }
}
