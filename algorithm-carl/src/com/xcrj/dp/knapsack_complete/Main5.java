package com.xcrj.dp.knapsack_complete;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/coin-change/
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 1种面值的硬币可以选择多次
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 背包最大承重 amount
 * 物品重量 coins
 * 每个物品可以选多次
 */
public class Main5 {
    public int coinChange(int[] coins, int amount) {
        int bagSize = amount;
        int[] weights = coins;
        // dp[金额]=最少硬币数量
        int[] dp = new int[bagSize + 1];
        // 求最小
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;// 总金额为0，0个硬币
        // 求凑成amount的硬币组合，与顺序无关，将物品或bag放到第1个for都可以
        for (int i = 0; i < weights.length; i++) {
            for (int j = weights[i]; j < bagSize + 1; j++) {
                if (dp[j - weights[i]] != Integer.MAX_VALUE) {
                    // ! 求总的方法数不用+values[i] 求 最大最小方法数需要+values[i]
                    // 选择了第i个物品，选择了第i个硬币，硬币数量+1
                    dp[j] = Math.min(dp[j], 1 + dp[j - weights[i]]);
                }
            }
        }

        return dp[bagSize] == Integer.MAX_VALUE ? -1 : dp[bagSize];
    }
}
