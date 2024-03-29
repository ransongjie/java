package com.xcrj.knapsack_complete;

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
 */
public class Main3 {
    public int combinationSum4(int[] nums, int target) {
        //dp数组
        int[] dp = new int[target + 1];
        //初始状态
        dp[0] = 1;//target=0, nums中1个元素也不选也是1中方法
        //状态转移
        for (int i = 1; i < target + 1; i++) {//求排列，先for背包
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) dp[i] += dp[i - nums[j]];
            }
        }
        //结果
        return dp[target];
    }
}
