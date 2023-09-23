package com.xcrj.str;

/**
 * 测试 String intern()
 */
public class Test1 {
    public static void main(String[] args) {
        //字符串拼接 进行了优化
        String str4 = new String("xcrj4") + "xcrj4";
        str4.intern();
        String str5 = "xcrj4xcrj4";
        System.out.println(str4 == str5);// true

        String str = new String("xcrj");
        str.intern();
        String str1 = "xcrj";
        System.out.println(str1 == str);// false

        StringBuilder sb = new StringBuilder();
        sb.append("abcd");
        String str6 = sb.toString();
        str6.intern();
        String str7 = "abcd";
        System.out.println(str6 == str7);// false
    }
}
