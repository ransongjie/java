package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 099. 最小路径之和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 一个机器人每次只能向下或者向右移动一步。
 *
 * 分析
 * - 注意，机器人只能往下或右走
 */
public class Solution99 {
    /**
     * 动态规划数组
     * dp[i][j]表示从左上角出发到(i,j)位置的最小路径和
     * <p>
     * 边界条件
     * dp[0][0]=grid[0][0]
     * 当 i>0 且 j=0 时，dp[i][0]=dp[i−1][0]+grid[i][0]，一直往下走
     * 当 i=0 且 j>0 时，dp[0][j]=dp[0][j−1]+grid[0][j]，一直往右走
     * <p>
     * 状态转移方程
     * 当i>0且j>0时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]
     * dp[i−1][j]：上一步往下走到达grid[i][j],则一定上一步一定在grid[i-1][j]
     * dp[i][j−1]：上一步往右走到达grid[i][j],则一定上一步一定在grid[i][j−1]
     */
    public int minPathSum(int[][] grid) {
        // 特殊情况处理
        if (null == grid) {
            return 0;
        }
        if (0 == grid.length) {
            return 0;
        }
        if (0 == grid[0].length) {
            return 0;
        }

        // 动态规划数组
        // dp[i][j]表示从左上角出发到(i,j)位置的最小路径和
        int row = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[row][column];

        // 边界条件
        // 初始点
        dp[0][0] = grid[0][0];
        // 一直往下走。1，初始点已经确定
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 一直往右走
        for (int j = 1; j < column; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        /**
         *  状态转移方程
         *  dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]
         *  上一步位置的最小路径值+当前位置的值
         *  1，第一行和第一列都已经被确定了
         */
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[row - 1][column - 1];
    }
}
