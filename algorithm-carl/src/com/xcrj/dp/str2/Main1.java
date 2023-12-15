package com.xcrj.dp.str2;

/**
 * https://leetcode.cn/problems/is-subsequence/
 * 判断子序列
 */
public class Main1 {
    // s是不是t的子序列，可以不连续
    public boolean isSubsequence(String s, String t) {
        //dp[i][j]=长度为i的子数组，是不是长度为j的子数组的子序列
        //以s[i-1]结尾的子数组是否 是 以t[j-1]结尾的子数组的子序列 的长度
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        dp[0][0] = 0;
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < t.length() + 1; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                // 相等则子序列长度扩大1，不等则依赖于前1个子数组
                dp[i][j] = (s.charAt(i - 1) == t.charAt(j - 1)) ? dp[i - 1][j - 1] + 1 : dp[i][j - 1];
            }
        }

        return dp[s.length()][t.length()] == s.length();
    }
}
