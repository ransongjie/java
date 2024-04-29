package com.xcrj.combination;

/**
 * https://leetcode.cn/problems/combination-sum-iv/submissions/
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * nums = [1, 2, 3]
 * target = 4
 * 所有可能的组合为： (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 因此输出为 7
 * <p>
 * 实质是排列数
 * 每个元素可以使用任意多次
 * <p>
 * 完全背包问题
 */
public class Main2 {
    public int combinationSum4(int[] nums, int target) {
        //dp[组成目标i]=方法数
        int[] dp = new int[target + 1];
        dp[0] = 1;//dp[组成目标0]=什么也不放也是1个方法
        //排列问题，先背包再物品
        for (int i = 1; i < target + 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
