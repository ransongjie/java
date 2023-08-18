package com.xcrj.interview150.dp_kadane;

/**
 * https://leetcode.cn/problems/maximum-subarray
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 */
public class Solution01 {
    /**
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int endiMax = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //dp[i]=以nums[i]结尾的连续子数组的最大和，才能没有后效性
            //两种情况，nums[i]一个元素作一个子数组，既是开头也是结尾
            //endiMax，nums[i]结尾
            endiMax = Math.max(nums[i], endiMax + nums[i]);
            max = Math.max(endiMax, max);
        }
        return max;
    }
}
