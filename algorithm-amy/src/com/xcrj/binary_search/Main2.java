package com.xcrj.binary_search;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Main2 {
    public int[] searchRange(int[] nums, int target) {
        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);
        if (leftBorder == -2 || rightBorder == -2) return new int[]{-1, -1};
        //左边界右移一步，右边界左移一步
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        return new int[]{-1, -1};
    }

    /**
     * @param nums
     * @param target
     * @return -2 未找到
     */
    private int getLeftBorder(int[] nums, int target) {
        int l = 0, r = nums.length - 1, leftBorder = -2;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                r = m - 1;
                leftBorder = r;
            } else {
                l = m + 1;
            }
        }
        return leftBorder;
    }

    private int getRightBorder(int[] nums, int target) {
        int l = 0, r = nums.length - 1, rightBorder = -2;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= target) {
                l = m + 1;
                rightBorder = l;
            } else {
                r = m - 1;
            }
        }
        return rightBorder;
    }
}
