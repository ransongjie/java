package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 095. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 
 * 注意：子序列，字母可以间隔，不相邻
 */
public class Solution95 {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[0~i字符串][0~j字符串]=最长公共子序列。
        // +1，因为空串。空串和任何字符串的公共序列长度为0
        // dp[0][]=0,dp[][0]=0
        int[][] dp=new int[text1.length()+1][text2.length()+1];

        // dp从空串开始。text1，text2从1个字符开始
        for(int i=1;i<=text1.length();i++){
            char a=text1.charAt(i-1);
            for(int j=1;j<=text2.length();j++){
                char b=text2.charAt(j-1);
                // text1的第i-1个字符=text2的第j-1个字符，相同公共子序列长度扩大
                if(a==b)dp[i][j]=dp[i-1][j-1]+1;
                // max(text1[0~i-1]和text2[0~j]的公共子序列，text1[0~i]和text2[0~j-1]的公共子序列)
                else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
