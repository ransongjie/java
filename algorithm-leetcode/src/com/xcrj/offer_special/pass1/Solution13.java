package com.xcrj.offer_special.pass1;

/**
 * 给定一个二维矩阵 matrix，计算其子矩形范围内元素的总和
 * 已知：该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 */
public class Solution13 {
    /**
     * 一维前缀和
     * preSum[j]+k=preSum[i], k是子矩阵行和
     */
    static class NumMatrix1 {
        // 所有行前缀和
        int[][] preSum;

        public NumMatrix1(int[][] matrix) {
            // 所有行前缀和
            preSum = new int[matrix.length][matrix[0].length + 1];
            // 没有任何元素是前缀和为0
            for (int i = 0; i < matrix.length; i++) {
                preSum[i][0] = 0;
            }
            // 求所有行前缀和
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    // preSum[i][1], 行前缀和有1个元素时
                    preSum[i][j + 1] = preSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sumK = 0;
            // 遍历所有行 求行前缀和 的和
            for (int i = row1; i <= row2; i++) {
                // 每行的前缀和
                sumK += preSum[i][col2 + 1] - preSum[i][col1];
            }

            return sumK;
        }
    }

    /**
     * 二维前缀和
     * 2*preSum[i][j]+k=preSum[i][n]+preSUm[m][j]
     */
    static class NumMatrix2 {
        // 二维前缀和
        int[][] preSum;

        public NumMatrix2(int[][] matrix) {
            // 所有行前缀和
            preSum = new int[matrix.length + 1][matrix[0].length + 1];
            // 没有任何元素是前缀和为0
            preSum[0][0] = 0;

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    // - preSum[i][j]原因 preSum[i][j + 1] 和 preSum[i + 1][j]都含有preSum[i][j]
                    preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] 因为都包含preSum[row1][col1]所以多减去了1个preSum[row1][col1]
            return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
        }
    }

    public static void main(String[] args) {
        // 一维前缀和
        NumMatrix1 numMatrix1 = new NumMatrix1(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}});
        // 输出8
        System.out.println(numMatrix1.sumRegion(2, 1, 4, 3));

        // 二维前缀和
        NumMatrix2 numMatrix2 = new NumMatrix2(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}});
        // 输出8
        System.out.println(numMatrix2.sumRegion(2, 1, 4, 3));
    }
}
