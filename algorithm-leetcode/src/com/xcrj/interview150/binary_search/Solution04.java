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

    public int[] searchRange2(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1, -1};

        int n = nums.length, l = 0, r = n - 1, first, last;

        // 先找到左边界
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if (nums[l] != target) return new int[]{-1, -1};
        else first = l;

        // 再找到右边界
        r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) l = mid;
            else r = mid - 1;
        }
        if (nums[r] != target) return new int[]{-1, -1};
        else last = r;

        return new int[]{first, last};
    }
}
