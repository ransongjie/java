package com.xcrj.pointer2;

/**
 * https://leetcode.cn/problems/remove-element/
 * 移除元素
 */
public class Main0 {
    public int removeElement(int[] nums, int val) {
        int i = 0, j = 0;//i是边界，j负责遍历
        for (; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public int removeElement1(int[] nums, int val) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            // 过滤掉不等于val的元素，找到边界
            while (l <= r && nums[l] != val) l++;
            // 过滤掉等于val的元素，找到不等于val的元素
            while (l <= r && nums[r] == val) r--;
            // 复制
            if (l < r) {// 相等不用复制
                nums[l++] = nums[r--];
            }
        }
        return l;
    }
}
