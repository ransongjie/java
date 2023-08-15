package com.xcrj.interview150.dpn;

/**
 * https://leetcode.cn/problems/unique-paths-ii
 * 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 有障碍物
 */
public class Solution3 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        // dp[i][j]=从左上角(0,0)走到(i,j)的路径数量
        int[][] dp = new int[m][n];

        // 一直往下走
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        // 一直往右走
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) break;
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //有障碍，从(0,0)到不了(i,j)
                if (obstacleGrid[i][j] == 1) continue;
                // 从(0,0)到(i,j)路径数量=从上来的路径数量+从左来的路径数量
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
