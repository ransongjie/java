package com.xcrj.pass2.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/target-sum/submissions/
 * 494. 目标和
 */
public class Main4 {
    /**
     * 求方法数
     * <p>
     * 问题转换
     * - 一定有 left组合 - right组合 = target, right可以为0
     * - left - (sum - left) = target 推导出 left = (target + sum)/2 ，装满容量为 left 的背包，有几种方法
     * bagSize=left
     * weight=nums
     * value=nums
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // nums所有元素全添加-或+，都凑不出target
        if (Math.abs(target) > sum) return 0;
        // left=(target+sum)/2 && left是整数(left不能是小数)
        if (((target + sum) & 1) == 1) return 0;

        // 背包问题
        int bagSize = Math.abs((target + sum) >> 1);//保证bagSize非负
        int[] weight = nums;
        int n = weight.length;
        //dp数组
        int[] dp = new int[bagSize + 1];
        //初始状态
        //背包承重为0=什么都不放也是一种方法
        dp[0] = 1;
        //状态转移
        for (int i = 0; i < n; i++) {//i=0
            for (int j = bagSize; j >= weight[i]; j--) {
                // 方法数
                dp[j] += dp[j - weight[i]];
            }
        }
        //结果
        return dp[bagSize];
    }
}
