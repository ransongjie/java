package com.xcrj.offer_special.pass4;

/**
 * 和大于等于 target 的最短子数组
 * 和大于等于 target 的子数组的最短长度
 */
public class Solution008 {
    /**
     * 窗口
     * 先满足要求，再破坏要求，以达到最低要求
     * 先满足和大于等于target，再和小于target，以达到最低要求
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, j = 0, n = nums.length, sum = 0, r = Integer.MAX_VALUE;
        while (j < n) {
            sum += nums[j];
            while (sum >= target) {
                r = Math.min(r, j - i + 1);
                sum -= nums[i++];
            }
            j++;
        }
        return r == Integer.MAX_VALUE ? 0 : r;
    }
}
