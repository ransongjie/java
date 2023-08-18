package com.xcrj.interview150.binary_search;

/**
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array
 * 寻找旋转排序数组中的最小值
 */
public class Solution05 {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (r + l) / 2;
            // 一定在第1段或第2段中
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
