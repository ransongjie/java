package com.xcrj.pass2.array.d_min_len;

/**
 * 长度最小的子数组
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class Main0 {
    /**
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int r = 0, l = 0, sum = 0, ans = Integer.MAX_VALUE;
        //两步走，先满足甚至破坏要求，再求最小
        for (; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= s) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
