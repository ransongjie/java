package com.xcrj.dp.max_array;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 * 最长公共子序列
 * dp[i][j] 长度为i 长度为j
 */
public class Main4 {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j]=text1[0~i-1]与text2[0~j-1]的最长公共子序列=长度为i 与 长度为j 的最长公共子序列
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i < text1.length() + 1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < text2.length() + 1; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
