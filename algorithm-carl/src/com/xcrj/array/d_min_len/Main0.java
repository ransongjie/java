package com.xcrj.array.d_min_len;

/**
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 * 长度最小的子数组
 */
public class Main0 {
    // 滑动窗口
    public int minSubArrayLen(int s, int[] nums) {
        // 先满足条件，再破坏条件
        int r = 0, l = 0, sum = 0, ans = Integer.MAX_VALUE;
        for (; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= s) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;// 存在不满足要求的情况
    }
}
