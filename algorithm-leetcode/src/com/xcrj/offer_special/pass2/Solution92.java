package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 092. 翻转字符
 * 如果一个由'0' 和 '1'组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是单调递增的。
 * 
 * 我们给出一个由字符 '0' 和 '1' 组成的字符串s，
 * 保证递增：'0'后面只能是'0', '1'后面可以是'0'或'1'
 * 
 * 返回保证 s 单调递增 的最小翻转次数。
 */
public class Solution92 {
    // 动态规划+滚动数组
    public int minFlipsMonoIncr(String s) {
        // 初始化，翻转为0的次数为0，翻转为1的次数为0
        int flip0=0,flip1=0;

        for(int i=0;i<s.length();i++){
            int flip0New=flip0;
            int flip1New=Math.min(flip0,flip1);
            char c=s.charAt(i);
            // 遇到'1'，翻转为0的次数+1
            if('1'==c)flip0New++;
            // 遇到'0'，翻转为1的次数+1
            else flip1New++;
        
            flip0=flip0New;
            flip1=flip1New;
        }

        return Math.min(flip0,flip1);
    }
}
