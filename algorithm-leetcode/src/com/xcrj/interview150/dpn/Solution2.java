package com.xcrj.interview150.dpn;

/**
 * https://leetcode.cn/problems/minimum-path-sum
 * 最小路径和
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 */
public class Solution2 {

    public int minPathSum(int[][] grid) {
        // 特殊情况处理
        if (null == grid) return 0;
        if (0 == grid.length) return 0;
        if (0 == grid[0].length) return 0;

        // dp[i][j]=从左上角出发到(i,j)位置的最小路径和
        int row = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[row][column];

        dp[0][0] = grid[0][0];
        // 一直往下走
        for (int i = 1; i < row; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        // 一直往右走
        for (int j = 1; j < column; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                //从(0,0)到(i,j)最小路径和=min(从上到(i,j)路径和,从左到(i,j)路径和)+此处路径长度
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[row - 1][column - 1];
    }
}
