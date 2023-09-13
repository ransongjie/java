package com.xcrj.dp.knapsack_complete;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/perfect-squares/
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量。
 *
 * 背包：n
 * 物品重量：i*i
 * 顺序：求组合
 * 求最小方法数
 */
public class Main6 {
    /**
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int bagSize = n;
        int[] dp = new int[bagSize + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // 求组合。i*i是物品重量
        for (int i = 1; i * i <= n; i++) {// ! 不从0开始，从1开始
            for (int j = i * i; j < bagSize + 1; j++) {
                // 不需要if条件，n一定可以由n个1组成
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        return dp[bagSize];
    }
}
