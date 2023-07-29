package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 088. 爬楼梯的最少成本
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
 * 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 */
public class Solution88 {

    /**
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     * 动态规划+动态规划数组
     */
    public int minCostClimbingStairs1(int[] cost) {
        /**
         * cost[0],dp[0]到达第0个台阶的最小花费
         * cost[0],dp[1]到达第1个台阶的最小花费
         * ...
         * cost[n-1],dp[n-1]到达第n-1个台阶的最小花费
         * dp[n]到达楼顶的最小花费
         */
        int[] dp = new int[cost.length + 1];
        // 可以选择下标0或1作为初始台阶
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            // 一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
            // 登上第i个楼梯的最小花费=min{登上第i-1个楼梯的最小花费+登上第i-1个楼梯的体力,登上第i-2个楼梯的最小花费+登上第i-2个楼梯的体力}
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[cost.length];
    }

    /**
     * 动态规划，滚动数组
     * 滚动数组：使用prev，curr，next交替记录值
     */
    public int minCostClimbingStairs2(int[] cost) {
        // 可以选择下标0或1作为初始台阶
        int prev = 0;
        int curr = 0;
        for (int i = 2; i <= cost.length; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }

        return curr;
    }
}
