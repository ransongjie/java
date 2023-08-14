package com.xcrj.interview150.matrix;

/**
 * https://leetcode.cn/problems/rotate-image
 * 旋转图像 90度
 */
public class Solution20 {
    /**
     * 使用翻转代替旋转
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length, tmp;
        //上下水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }
        //主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
