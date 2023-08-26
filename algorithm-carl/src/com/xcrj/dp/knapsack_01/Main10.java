package com.xcrj.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/target-sum/submissions/
 * 494. 目标和
 */
public class Main10 {
    public int findTargetSumWays(int[] nums, int target) {
        /**
         * 问题转换
         * - 一定有 left组合 - right组合 = target, right可以为0
         * - left - (sum - left) = target 推导出 tgt=left = (target + sum)/2 ，装满容量为 left 的背包，有几种方法
         * 背包：tgt
         * 物品：nums中的元素值
         * - 重量：nums中的元素值
         * - 价值：nums中的元素值
         * 物品要么选要么不选只有01两种状态
         * 物品只能选1次
         */
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum) return 0;
        // (target + sum)/2是小数，从nums中选不能组合成小数
        if (((target + sum) & 1) == 1) return 0;
        int tgt = ((target + sum) >> 1);
        // ！！！保证大于0
        tgt = tgt < 0 ? -tgt : tgt;
        // dp数组
        int[] dp = new int[tgt + 1];
        // 初始状态, 没有重量，1种方法，什么都不选
        dp[0] = 1;
        // 状态转移
        for (int i = 0; i < n; i++) {
            for (int j = tgt; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        // 方法数
        return dp[tgt];
    }
}
