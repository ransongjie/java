package com.xcrj.dp.basic;

public class Main4 {
    /**
     * 对比 Main3 遇到障碍就是0
     *
     * @param ass
     * @return
     */
    public int uniquePathsWithObstacles(int[][] ass) {
        int m = ass.length, n = ass[0].length;
        // 起点重点有障碍
        if (ass[0][0] != 0 || ass[m - 1][n - 1] != 0) return 0;

        // dp数组
        int[][] dp = new int[m][n];
        // 初始状态
        for (int i = 0; i < n && ass[0][i] == 0; i++) {// 一直往右走
            dp[0][i] = 1;
        }
        for (int i = 0; i < m && ass[i][0] == 0; i++) {// 一直往下走
            dp[i][0] = 1;
        }
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 从上来，从左来
                dp[i][j] = ass[i][j] != 0 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // 结果
        return dp[m - 1][n - 1];// 从 (0,0) 走到 (m-1,n-1)
    }
}
