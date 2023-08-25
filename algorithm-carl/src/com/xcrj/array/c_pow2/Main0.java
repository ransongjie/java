package com.xcrj.array.c_pow2;

/**
 * 有序数组的平方
 * https://leetcode.cn/problems/squares-of-a-sorted-array
 */
public class Main0 {

    public int[] sortedSquares(int[] nums) {
        int l = 0, r = nums.length - 1, k = r;
        int[] ans = new int[nums.length];
        // 二路归并
        while (l <= r) {
            if (nums[l] * nums[l] >= nums[r] * nums[r]) {
                ans[k--] = nums[l] * nums[l++];
            } else {
                ans[k--] = nums[r] * nums[r--];
            }
        }
        return ans;
    }
}
