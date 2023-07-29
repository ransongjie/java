package com.xcrj.test;

/**
 * 编译优化 针对字符类型
 */
public class CompileStr {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        String xcrj=new String("xcrj");
        String xcrjIn=xcrj.intern();
        String xcrj2="xcrj";
        System.out.println(xcrjIn==xcrj2);
    }
}
