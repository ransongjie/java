package com.xcrj.array.a_binary_search;

/**
 * 搜索插入位置
 */
public class Main01 {
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        for (; i < nums.length; i++) {
            /**
             * target 插入元素之前
             * target 等于某个元素
             */
            if (nums[i] >= target) return i;
        }
        // target 插入到最后
        return i;
    }

    public int searchInsert1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;//闭区间
        while (left <= right) {
            int middle = left + (right - left) / 2;//防止越界
            if (nums[middle] == target) return middle;//target 等于某个元素
            else if (nums[middle] > target) right = middle - 1;// 闭区间, middle已经比较过
            else left = middle + 1;// 闭区间, middle已经比较过
        }
        /**
         * target 插入元素之前
         * target 插入到最后
         */
        return right + 1;// right=middle-1 所以+1
    }

    public int searchInsert2(int[] nums, int target) {
        int left = 0, right = nums.length;//右开区间
        while (left < right) {
            int middle = left + (right - left) / 2;//防止越界
            if (nums[middle] == target) return middle;//target 等于某个元素
            else if (nums[middle] > target) right = middle;// 右开区间, right=middle不会被比较
            else left = middle + 1;// 右开区间, middle已经比较过
        }
        /**
         * target 插入元素之前
         * target 插入到最后
         */
        return right;// right=middle 所以不+也不-
    }
}
