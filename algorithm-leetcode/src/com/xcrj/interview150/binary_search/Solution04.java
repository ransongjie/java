package com.xcrj.interview150.binary_search;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 在排序数组中查找元素的第一个和最后一个位置
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 */
public class Solution04 {
    public int[] searchRange(int[] nums, int target) {
        int l = 0, r = nums.length - 1, m, first = -1, last = -1;
        // 找第一个等于target的位置
        while (l <= r) {
            m = (l + r) / 2;
            if (nums[m] == target) {
                first = m;
                r = m - 1; //相等继续往左去找
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        // 最后一个等于target的位置
        l = 0;
        r = nums.length - 1;
        while (l <= r) {
            m = (l + r) / 2;
            if (nums[m] == target) {
                last = m;
                l = m + 1; //相等继续往右去找
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return new int[]{first, last};
    }
}
