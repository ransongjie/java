package com.xcrj.dp.knapsack_complete;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 * 爬楼梯。需要 n 阶你才能到达楼顶
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * n是背包总承重
 * 1,2 是物品重量，每个物品可以选择多次
 */
public class Main4 {
    public int climbStairs(int n) {
        int[] weights = {1, 2};
        int bagSize = n;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;
        // 先走1步再走2步 与 先走2步再走1步 顺序不同，需要将背包承重放到外for
        for (int i = 0; i < bagSize + 1; i++) {
            for (int j = 0; j < weights.length; j++) {
                if (i >= weights[j]) {
                    dp[i] += dp[i - weights[j]];
                }
            }
        }

        return dp[bagSize];
    }
}
