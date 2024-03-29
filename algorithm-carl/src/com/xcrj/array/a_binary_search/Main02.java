package com.xcrj.array.a_binary_search;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Main02 {
    public int[] searchRange(int[] nums, int target) {
        int lb = getLeftBorder(nums, target);
        int rb = getRightBorder(nums, target);
        // 越界，最左，最右
        if (lb == -2 || rb == -2) return new int[]{-1, -1};
        // lb=middle+1 所以-1, rb=middle-1 所以-1
        if (rb - lb > 1) return new int[]{lb + 1, rb - 1};
        // 不存在
        return new int[]{-1, -1};
    }

    private int getLeftBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1, leftBorder = -2;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] >= target) {// =纳入！！！
                right = middle - 1;
                leftBorder = right;// 左边界，尽可能往左移动，找等于
            } else {
                left = middle + 1;
            }
        }

        return leftBorder;
    }

    private int getRightBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1, rightBorder = -2;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] <= target) {// =纳入！！！
                left = middle + 1;
                rightBorder = left;// 右边界，尽可能往右移动，找等于
            } else {
                right = middle - 1;
            }
        }

        return rightBorder;
    }
}
