package com.xcrj.dp.basic;

public class Main3 {
    public static int uniquePaths(int m, int n) {
        // dp数组
        int[][] dp = new int[m][n];
        // 初始状态
        for (int i = 0; i < n; i++) {// 一直往右走
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {// 一直往下走
            dp[i][0] = 1;
        }
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 从上来，从左来
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // 结果
        return dp[m - 1][n - 1];// 从 (0,0) 走到 (m-1,n-1)
    }
}
