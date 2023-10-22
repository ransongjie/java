package com.xcrj.pass2.dp.basic;

/**
 * https://leetcode.cn/problems/unique-paths/
 * 不同路径
 */
public class Main3 {
    public static int uniquePaths(int m, int n) {
        // dp数组
        int[][] dp = new int[m][n];
        // 初始状态
        // 一直往右走
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 一直往下走
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 从上到[i][j]的方法数+从左到[i][j]的方法数
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // 结果
        return dp[m - 1][n - 1];
    }

}
