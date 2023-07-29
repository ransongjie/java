package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 013. 二维子矩阵的和
 * - 子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)
 */
public class Solution13 {
    /**
     * 将子矩阵每一行的和相加
     * 一维前缀和
     * - 当前和-k=前缀和，k是子矩阵的每一行
     */
    class NumMatrix1 {
        int[][] preSum;

        public NumMatrix1(int[][] matrix) {
            // +1，初始前缀和
            preSum = new int[matrix.length][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                preSum[i][0] = 0;
            }
            // 构建前缀和数组
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    // 逐行逐列构建前缀和 j+1表示[0,j]的元素和
                    preSum[i][j + 1] = preSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // 将子矩阵每一行的和相加
            int r = 0;
            for (int i = row1; i <= row2; i++) {
                r += preSum[i][col2 + 1] - preSum[i][col1];
            }
            return r;
        }
    }

    /**
     * 二维前缀和
     * - preSum[m][n]=preSum[m][j]+preSum[i][n]-preSum[i][j]+k
     */
    class NumMatrix2 {
        int[][] preSum;

        public NumMatrix2(int[][] matrix) {
            // 二维前缀和初始化
            preSum = new int[matrix.length + 1][matrix[0].length + 1];
            preSum[0][0] = 0;
            // 构建二维前缀和
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // preSum[row1][col2+1]和preSum[row2+1][col1]都包含preSum[row1][col1]
            return preSum[row2+1][col2+1]-preSum[row1][col2+1]-preSum[row2+1][col1]+preSum[row1][col1];
        }
    }
}
