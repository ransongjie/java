package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 097. 子序列的数目
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * 分析：
 * - s删除n个元素后等于t，n可以为0。
 * - t作为s的子序列在s中出现的次数
 * - s[i:]的子序列指的是可以删除部分元素后形成的序列
 * 
 * 输入，字符串s和t
 * 输出，t在s中出现的次数
 */
public class Solution97 {
    // 动态规划
    public int numDistinct(String s, String t) {
        if(s.length()<t.length()) return 0;

        // 动态规划数组。dp[i][j]表示在s[i:]的子序列中t[j:]出现的次数。从右边往左
        int[][] dp=new int[s.length()+1][t.length()+1];

        // 边界条件。t[len:]是空串，s[0:] s[1:] s[len:]
        // xcrj <=
        for(int i=0;i<=s.length();i++) dp[i][t.length()]=1;

        // 状态转移。
        for(int i=s.length()-1;i>=0;i--){
            for(int j=t.length()-1;j>=0;j--){
                // s第i个字符=t第j个字符，t[j+1:]在s[i+1:]中出现次数
                if(s.charAt(i)==t.charAt(j)) dp[i][j]=dp[i+1][j+1]+dp[i+1][j];
                else dp[i][j]=dp[i+1][j];
            }
        }

        // xcrj 从右往左
        return dp[0][0];
    }
}
