package com.xcrj.dp.str2;

/**
 * https://leetcode.cn/problems/distinct-subsequences/
 * 不同的子序列
 */
public class Main2 {
    // s中t出现的次数
    public int numDistinct(String s, String t) {
        //dp[i][j]=长度为i的s子数组 中 出现长度为j的t子数组 的子序列个数
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;// 空串在子数组中出现1次
        }
        for (int j = 1; j < t.length(); j++) {
            dp[0][j] = 0;// 非空串在空串中出现0次
        }
        dp[0][0] = 1;// 空串在空串中出现1次
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                // 相等 使用相等字符则都看前面的数量 不使用相等字符串则看s前面的数量
                dp[i][j] = (s.charAt(i - 1) == t.charAt(j - 1)) ? dp[i - 1][j - 1] + dp[i - 1][j] : dp[i - 1][j];
            }
        }
        return dp[s.length()][t.length()];
    }
}
