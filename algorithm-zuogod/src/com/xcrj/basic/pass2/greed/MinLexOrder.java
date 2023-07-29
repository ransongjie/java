package com.xcrj.basic.pass2.greed;

import java.util.Arrays;

/**
 * 字符串拼接的最小字典序结果
 * 直接按照字典序排序字符串数组即可
 */
public class MinLexOrder {
    public static void main(String[] args) {
        String[] ss={"bk","at","cs"};
        String r=minLexOrder(ss);
        System.out.println(r);
    }

    public static String minLexOrder(String[] ss) {
        if(ss==null || ss.length==0)return null;
        Arrays.sort(ss,(o1,o2)->o1.compareTo(o2));//按字典序进行比较
        StringBuilder sb=new StringBuilder();
        for (String string : ss) {
            sb.append(string);
        }
        return sb.toString();
    }
}
