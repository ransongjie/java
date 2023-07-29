package com.xcrj.offer_special.pass4;

/**
 * 二维子矩阵的和
 */
public class Solution013 {
    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        int sum = numMatrix.sumRegion(2, 1, 4, 3);
        System.out.println();
    }
}

class NumMatrix {
    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int i = 0, j, m = matrix.length, n = matrix[0].length;
        preSum = new int[m + 1][n + 1];
        preSum[0][0] = 0;
        for (; i < m; i++) {
            for (j = 0; j < n; j++) {//j=0
                preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
    }
}
