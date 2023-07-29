package com.xcrj.offer_special.pass3;

/**
 *  剑指 Offer II 001. 整数除法
 *  给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 *  整数除法的结果应当截去（truncate）其小数部分，truncate(8.345) = 8, truncate(-2.7335) = -2
 *  - xcrj 使用位移运算符 结果没有小数
 *  假设我们的环境只能存储 32 位有符号整数 数值范围是 [−2^31, 2^31−1]。本题中，如果除法结果溢出，则返回 2^31 − 1
 */
public class Solution1{
    public int divide(int a, int b) {
        //结果超过存储范围，直接返回最大值
        if(a==Integer.MIN_VALUE&&b==-1) return Integer.MAX_VALUE;

        //被除数<除数=0, 2/3=0
        // if(Math.abs(a)<Math.abs(b)) return 0;
        
        //后续使用负数存储，预先存储计算结果符号
        boolean positive=false;
        if(a>0&&b>0) positive=true;
        if(a<0&&b<0) positive=true;

        //使用负数存储
        // xcrj long, mb左移可能越界
        long ma=a>0?-a:a;
        long mb=b>0?-b:b;
        if(ma>mb) return 0;

        //找到位移边界值 位移多少（n）仍然保证 被除数>除数
        //xcrj 被除数>除数*2^n 剩余被除数=被除数-除数*2^n 剩余被除数继续是否能被除数整数
        //xcrj 位运算符不改变原值
        int r=0;
        int shift=31;
        while(ma<=mb){
            //xcrj 找边界，保证ma<mb，先让mb<ma，让mb从左一步步靠近ma，直到mb>ma
            while(ma>mb<<shift) shift--;
            ma-=mb<<shift;
            //xcrj 1<<shift 求2^n
            r+=1<<shift;
        }
    
        return positive?r:-r;
    }
}