package com.xcrj.interview150.dp_kadane;

/**
 * https://leetcode.cn/problems/maximum-sum-circular-subarray
 * <p>
 * 环形子数组的最大和
 * <p>
 * 不同于环形房屋偷盗，环形房屋偷盗必须间隔偷
 */
public class Solution02 {
    /**
     * 最大子数组 不成环
     * 最大子数组 成环，最小子数组不成环，最大子数组=total-最小子数组
     * 特殊情况，全是负值
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0, curMax = 0, curMin = 0, maxSum = nums[0], minSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            curMax = Math.max(curMax + nums[i], nums[i]);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + nums[i], nums[i]);
            minSum = Math.min(minSum, curMin);
            total += nums[i];
        }
        //当maxSum<0时, total-minSum=0
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

}
