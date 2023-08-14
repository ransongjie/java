package com.xcrj.interview150.slide_window;

/**
 * https://leetcode.cn/problems/minimum-size-subarray-sum
 * 长度最小的子数组
 */
public class Solution16 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int res = Integer.MAX_VALUE, l = 0, r = 0, sum = 0;
        //先满足条件
        while (r < n) {
            sum += nums[r];
            //再破坏条件
            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
            r++;
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
