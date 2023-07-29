package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 068. 查找插入位置
 * - 给定一个排序的整数数组 nums和一个整数目标值 target ，请在数组中找到target，并返回其下标。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * - 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class Solution68 {
    /**
     * 二分查找，查找>=target的值的下标
     * - 为什么么是>=target: 因为当target不再nums[]中时，需要插入到nums[mid]的位置，nums[mid]~nums[len-1]在target的后面
     * 初始时，o下标为len，即假设>=nums[]中的所有值
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1, o = nums.length;
        while (l <= r) {
            // ！运算符优先级，含有位运算时，括号加上
            int mid = ((r - l) >> 1) + l;
            // target在mid的左边，则继续二分查找左侧
            if (target <= nums[mid]) {
                o = mid;
                r = mid - 1;
            }
            // target在mid的右边，则继续二分查找右侧
            else {
                l = mid + 1;
            }
        }
        return o;
    }
}
