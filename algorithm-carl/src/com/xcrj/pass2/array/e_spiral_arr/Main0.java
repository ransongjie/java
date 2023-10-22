package com.xcrj.pass2.array.e_spiral_arr;

/**
 * https://leetcode.cn/problems/spiral-matrix-ii/
 * 螺旋矩阵 II
 */
public class Main0 {
    /**
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        //start每圈的起点，loop第n圈，v填入矩阵的值，右开区间
        int start = 0, loop = 0, v = 1, row, col;
        int[][] ans = new int[n][n];
        while (loop < n / 2) {
            //由外圈向内圈循环
            loop++;
            for (row = start, col = start; col < n - loop; col++) {
                ans[row][col] = v++;
            }
            for (row = start; row < n - loop; row++) {
                ans[row][col] = v++;
            }
            for (; col >= loop; col--) {
                ans[row][col] = v++;
            }
            for (; row >= loop; row--) {
                ans[row][col] = v++;
            }
            //更新起点
            start++;
        }
        //最中心元素
        if ((n & 1) == 1) {
            ans[start][start] = v;
        }
        return ans;
    }
}
