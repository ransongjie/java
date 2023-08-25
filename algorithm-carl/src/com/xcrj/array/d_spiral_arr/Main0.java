package com.xcrj.array.d_spiral_arr;

/**
 * https://leetcode.cn/problems/fruit-into-baskets/
 * 水果成篮
 */
public class Main0 {
    public int[][] generateMatrix(int n) {
        int start = 0, loop = 0, v = 1, col, row;//控制左边界，控制右边界，左闭右开，变量控制
        int[][] ans = new int[n][n];
        while (loop < n / 2) {
            loop++;
            // 从左到右，行不变列变，col in [l,n)
            for (col = start; col < n - loop; col++) {
                ans[start][col] = v++;
            }
            // 从上到下，行变列不变，row in [l,n)
            for (row = start; row < n - loop; row++) {
                ans[row][col] = v++;
            }
            // 从右往左，行不变列变，col in [col,loop-1)
            for (; col >= loop; col--) {
                ans[row][col] = v++;
            }
            // 从下往上，行变列不变, row in [row,loop-1)
            for (; row >= loop; row--) {
                ans[row][col] = v++;
            }
            //
            start++;
        }
        // 奇数 最中心元素，从左到右即可，不需要其它
        if ((n & 1) == 1) {
            ans[start][start] = v;
        }
        return ans;
    }
}
