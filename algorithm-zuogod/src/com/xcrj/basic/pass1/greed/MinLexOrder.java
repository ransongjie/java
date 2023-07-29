package com.xcrj.basic.pass1.greed;

import java.util.Arrays;

//字符串数组拼接的最小字典序
public class MinLexOrder {
    public static void main(String[] args) {
        String[] ss={"bk","at","cs"};
        String r=minLexOrder(ss);
        System.out.println(r);
    }

    public static String minLexOrder(String[] ss) {
        if(ss==null||ss.length==0) return null;
        //
        Arrays.sort(ss,(a,b)->(a+b).compareTo(b+a));
        //
        StringBuilder sb=new StringBuilder();
        //
        for (String s : ss) {
            sb.append(s);
        }
        //
        return sb.toString();
    }
}
