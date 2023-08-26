package com.xcrj.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/ones-and-zeroes/
 * 474. 一和零
 */
public class Main11 {
    public int findMaxForm(String[] strs, int m, int n) {
        /**
         * 问题转换
         * 重量：1的个数，0的个数
         * 价值：=重量
         * 物品：字符串
         */
        // dp数组，dp[i][j]：最多有i个0和j个1的最多字符串数量
        int[][] dp = new int[m + 1][n + 1];
        // 初始状态
        // 状态转移
        int num0, num1;
        for (String str : strs) {
            // 统计str中0的数量1的数量
            num0 = 0;
            num1 = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') num0++;
                else num1++;
            }
            // 倒序
            for (int i = m; i >= num0; i--) {
                for (int j = n; j >= num1; j--) {
                    // 选第str字符串则+1，价值。num0, num1重量。
                    dp[i][j] = Math.max(dp[i][j], dp[i - num0][j - num1] + 1);
                }
            }
        }
        // 结果
        return dp[m][n];
    }
}
