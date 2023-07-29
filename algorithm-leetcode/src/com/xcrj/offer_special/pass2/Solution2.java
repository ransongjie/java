package com.xcrj.offer_special.pass2;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 剑指 Offer II 002. 二进制加法
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 进制加法：
 * 二进制逢二进一，商为进位，余数为当前值
 */
public class Solution2 {
    /**
     * 数组存储从左往右，计算从右往左
     */
    public String addBinary(String a, String b) {
        // 处理"0"字符串，字符串如果不是 "0" ，就都不含前导零。
        char[] acs=a.toCharArray();
        char[] bcs=b.toCharArray();
        if('0'==acs[0]){
            return b;
        }
        if('0'==bcs[0]){
            return a;
        }
        // 保证字符长度一致，右对齐
        char[]acs1=null;
        char[]bcs1=null;
        if(acs.length==bcs.length){
            acs1=acs;
            bcs1=bcs;
        }
        if(acs.length<bcs.length){
            bcs1=bcs;
            acs1=new char[bcs.length];
            System.arraycopy(acs,0,acs1,acs1.length-acs.length,acs.length);
        }
        if(acs.length>bcs.length){
            acs1=acs;
            bcs1=new char[acs.length];
            System.arraycopy(bcs,0,bcs1,bcs1.length-bcs.length,bcs.length);
        }
        System.out.println(Arrays.toString(acs1));
        System.out.println(Arrays.toString(bcs1));
        // 字符转数字
        int[] ais=new int[acs1.length];
        int[] bis=new int[acs1.length];
        for(int i=0;i<acs1.length;i++){
            switch (acs1[i]){
                case '0':
                    ais[i]=0;
                    break;
                case '1':
                    ais[i]=1;
                    break;
            }
        }
        for(int i=0;i<bcs1.length;i++){
            switch (bcs1[i]){
                case '0':
                    bis[i]=0;
                    break;
                case '1':
                    bis[i]=1;
                    break;
            }
        }
        // 保存结果，最高位可能进位，因此长度+1
        int[]rs=new int[ais.length+1];
        // 2进制运算，逢二进一
        for(int i=ais.length-1,k=rs.length-1;i>=0;i--,k--){
            // 商为进位
            rs[k-1]=(ais[i]+bis[i]+rs[k])/2;
            // 余数为值
            rs[k]=(ais[i]+bis[i]+rs[k])%2;
        }
        // 处理最高位进位
        if(1==rs[0]){
            return Arrays.stream(rs).mapToObj(String::valueOf).collect(Collectors.joining(""));
        }else {
            return Arrays.stream(Arrays.copyOfRange(rs,1,rs.length)).mapToObj(String::valueOf).collect(Collectors.joining(""));
        }
    }
}
