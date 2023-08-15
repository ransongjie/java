package com.xcrj.interview150.dpn;

import java.util.List;

/**
 * https://leetcode.cn/problems/triangle
 * 三角形最小路径和
 */
public class Solution1 {

    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // 第0行
        dp[0][0] = triangle.get(0).get(0);

        // 第i行
        for (int i = 1; i < triangle.size(); i++) {
            // 竖边
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            // 内部边
            for (int j = 1; j < i; j++) {
                //  到达(i,j)的最小路径和 = min(从上而来的最小路径和,从左上角而来的最小路径和)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }
            // 斜边
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        // 求到达底边的最小路径和
        // 初始值
        int minSum = dp[n - 1][0];
        for (int j = 1; j < triangle.size(); j++) minSum = Math.min(minSum, dp[n - 1][j]);

        return minSum;
    }
}
