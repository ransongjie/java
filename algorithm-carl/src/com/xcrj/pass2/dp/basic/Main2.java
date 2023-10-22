package com.xcrj.pass2.dp.basic;

/**
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 * 使用最小花费爬楼梯
 */
public class Main2 {
    public int minCostClimbingStairs(int[] cost) {
        // dp数组
        int[] dp = new int[cost.length + 1];
        // 初始状态
        dp[0] = 0;
        dp[1] = 0;
        // 状态转移
        for (int i = 2; i <= cost.length; i++) {
            // dp[i-1]到达i-1阶梯的最小成本。支付cost[i-1]成本才能到达i阶梯
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        // 结果
        return dp[cost.length];
    }

}
