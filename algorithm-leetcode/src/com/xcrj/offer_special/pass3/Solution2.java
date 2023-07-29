package com.xcrj.offer_special.pass3;

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
    public String addBinary(String a, String b) {
        // 处理"0"字符串
        if("0".equals(a)) return b;
        if("0".equals(b)) return a;
        // 字符串转储到字符数组，并右对齐
        char[]ras=null;
        char[]rbs=null;
        char[]as=a.toCharArray();
        char[]bs=b.toCharArray();
        if(as.length==bs.length){
            ras=as;
            rbs=bs;
        }
        if(as.length>bs.length){
            ras=as;
            rbs=new char[as.length];
            System.arraycopy(bs, 0, rbs, as.length-bs.length, bs.length);
        }
        if(as.length<bs.length){
            rbs=bs;
            ras=new char[bs.length];
            System.arraycopy(as, 0, ras, bs.length-as.length, as.length);
        }
        // 字符数组转整数数组
        int[]ias=new int[ras.length];
        int[]ibs=new int[ras.length];
        for(int i=0;i<ras.length;i++){
            switch(ras[i]){
                case '0':
                    ias[i]=0;
                break;
                case '1':
                    ias[i]=1;
                break;
            }
        }
        for(int i=0;i<rbs.length;i++){
            switch(rbs[i]){
                case '0':
                    ibs[i]=0;
                break;
                case '1':
                    ibs[i]=1;
                break;
            }
        }
        System.out.println(Arrays.toString(ias));
        System.out.println(Arrays.toString(ibs));
        // 二进制加法，(a+b)/2=商 是进位，(a+b)%2=余数是当前值
        // 可能最高位有进位 所以+1
        int[] ris=new int[ias.length+1];
        // xcrj 从低位计算到高位，倒着求解
        for(int i =ias.length-1,j=ris.length-1;i>=0;i--,j--){
            // xcrj 顺序不能调换
            ris[j-1]=(ias[i]+ibs[i]+ris[j])/2;
            ris[j]=(ias[i]+ibs[i]+ris[j])%2;
        }
        // 判断ris的最高位是否为1，不能输出011...
        if(ris[0]==1){
            return Arrays.stream(ris).mapToObj(String::valueOf).collect(Collectors.joining(""));
        }else{
            return Arrays.stream(Arrays.copyOfRange(ris, 1, ris.length)).mapToObj(String::valueOf).collect(Collectors.joining(""));
        }
    }
    
    public static void main(String[] args) {
        Solution2 solution2=new Solution2();
        String r=solution2.addBinary("11", "10");
        System.out.println(r);
    }
}
