package com.xcrj.dp;

/**
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 * 使用最小花费爬楼梯
 */
public class Main2 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // dp数组
        int[] dp = new int[n + 1];
        // 初始状态
        dp[0] = 0;
        dp[1] = 0;// 可从第0或第1出发，没有花费
        // 状态转移
        for (int i = 2; i <= n; i++) {
            // 从第i-1台阶而来需支付成本cost[i-1]
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        // 结果
        return dp[n];
    }
}
