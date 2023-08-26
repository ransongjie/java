package com.xcrj.dp.knapsack_01;

/**
 *
 */
public class Main9 {

    public int lastStoneWeightII(int[] stones) {
        /**
         * 问题转换 尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，0-1背包问题
         * 背包：target
         * 物品：石头
         * - 重量：石头重量
         * - 价值：石头重量
         * 物品要么选要么不选只有01两种状态
         * 物品只能选1次
         */
        int sum = 0, target, n = stones.length;
        for (int num : stones) {
            sum += num;
        }
        target = sum / 2;
        // dp数组
        // dp[j]=背包承重为j时的最大价值，物品重量=价值
        int[] dp = new int[target + 1];
        // 初始状态，只有第0个石头，从能够承担第0个石头重量的背包开始，不论多大的背包总价值都是第0个石头
//        for (int i = stones[0]; i < target + 1; i++) {
//            dp[i] = stones[0];
//        }
        // 状态转移
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(stones[i] + dp[j - stones[i]], dp[j]);
            }
        }
        // 结果，dp[target]=选择物品放入背包中重量尽可能接近sum/2
        return sum - 2 * dp[target];
    }
}
