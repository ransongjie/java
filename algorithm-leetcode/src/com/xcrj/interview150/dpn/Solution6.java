package com.xcrj.interview150.dpn;

/**
 * https://leetcode.cn/problems/maximal-square
 * 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 */
public class Solution6 {

    public int maximalSquare(char[][] matrix) {
        //全为1的最大正方形的边长，最大边长
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length, columns = matrix[0].length;
        //dp[i][j]=以(i,j)为右下角端点的最大边长
        int[][] dp = new int[rows][columns];

        int maxSide = 0;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxSide = 1;
            }
        }
        for (int i = 0; i < columns; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxSide = 1;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    //！以(i,j)为右下角端点的最大边长 = min((i-1,j),(i,j-1),(i-1,j-1)) + 1
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

}
