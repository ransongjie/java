package com.xcrj.pass2.dp.basic;

/**
 * https://leetcode.cn/problems/unique-paths-ii/
 * 不同路径2
 */
public class Main4 {
    /**
     * 障碍=ass[i][j]!=0，没有方法走到障碍处
     *
     * @param ass
     * @return
     */
    public int uniquePathsWithObstacles(int[][] ass) {
        int m = ass.length, n = ass[0].length;
        // dp数组
        int[][] dp = new int[m][n];
        // 初始状态
        for (int i = 0; i < n && ass[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m && ass[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = ass[i][j] != 0 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // 结果
        return dp[m - 1][n - 1];
    }

}
