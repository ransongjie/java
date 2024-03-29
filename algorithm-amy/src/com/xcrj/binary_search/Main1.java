package com.xcrj.binary_search;

/**
 * 搜索插入位置
 * https://leetcode.cn/problems/search-insert-position/
 */
public class Main1 {
    //nums正序
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }

    public int searchInsert1(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            else if (nums[m] > target) r = m - 1;
            else l = m + 1;
        }
        return r + 1;//
    }

    public int searchInsert2(int[] nums, int target) {
        int l = 0, r = nums.length;///
        while (l < r) {///
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            else if (nums[m] > target) r = m;///
            else l = m + 1;
        }
        return r;//
    }
}
