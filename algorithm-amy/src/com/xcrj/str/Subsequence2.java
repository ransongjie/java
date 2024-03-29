package com.xcrj.str;

/**
 * https://leetcode.cn/problems/distinct-subsequences/
 * 不同的子序列数量
 */
public class Subsequence2 {
    // s中t出现的次数
    public int numDistinct(String s, String t) {
        //dp[sLen][tLen]=s中t序列出现的次数，序列可不连续
        //dp[i][j]=s[0~i-1]中t[0~j-1]序列出现的次数
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;//空串在空串中出现1次，在非空串中出现1次
        }
        for (int i = 1; i < t.length() + 1; i++) {
            dp[0][i] = 0;//非空串在空串中出现0次；
        }
        dp[0][0] = 1;//空串在空串中出现1次；
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                //dp[i-1][j]=i-1串中j序列出现的次数
                dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] + dp[i - 1][j] : dp[i - 1][j];
            }
        }
        return dp[s.length()][t.length()];
    }
}
