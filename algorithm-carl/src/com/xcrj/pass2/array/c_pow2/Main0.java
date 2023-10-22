package com.xcrj.pass2.array.c_pow2;

/**
 * 有序数组的平方
 * https://leetcode.cn/problems/squares-of-a-sorted-array
 */
public class Main0 {
    public int[] sortedSquares(int[] nums) {
        //二路归并
        int[] ans = new int[nums.length];
        int l = 0, r = nums.length - 1, k = r;
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
