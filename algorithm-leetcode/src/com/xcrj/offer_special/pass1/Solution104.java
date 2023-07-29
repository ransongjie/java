package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 104. 排列的数目
 * 给定一个由 不同正整数组成的数组 nums ，和一个目标整数 target 。
 * 请从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 数组中的数字可以在一次排列中出现任意次，但是顺序不同的序列被视作不同的组合。
 * 题目数据保证答案符合 32 位整数范围。
 */
public class Solution104 {
    /**
     * 动态规划，分阶段
     * dp[target]=排列和等于target的方案数=dp[target-nums[j]]
     * 排列把nums[j]的每一个元素尝试放到排列的最后一位
     * <p>
     * dp[target-1]排列和等于target-1的方案数=dp[target-1-nums[j]]
     * 排列把nums[j]的每一个元素尝试放到排列的最后一位
     */
    public int combinationSum4(int[] nums, int target) {
        // 动态规划数组
        // dp[i]=选取的元素之和等于i的方案数，i从0到target，共target+1
        int[] dp = new int[target + 1];

        // 边界条件
        // dp[0]=1,元素之和为0，只有不选取任何元素这1种方式才能得到
        dp[0] = 1;

        // 遍历i从1到target，遍历j从nums[0]到nums[len-1]
        // dp[i]=dp[i-nums[j]],j从0到len，分情况累加
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                // 比i小的值才能放到排列中
                if (nums[j] <= i) dp[i] = dp[i] + dp[i - nums[j]];
            }
        }

        return dp[target];
    }
}
