package com.xcrj.interview150.arr_str;

import java.util.Arrays;

/**
 * https://leetcode.cn/studyplan/top-interview-150/
 * 删除排序数组中的重复项
 * {1,1,1,2,2,3} 》 {1,1,2,2,3}
 */
public class Solution01 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        Solution01 s01 = new Solution01();
        int r = s01.removeDuplicates(nums);
        System.out.println(r);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 双指针
     * pa完成位置
     * pb遍历位置
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 3) return n;
        int pa = 2, pb = 2;
        while (pb < n) {
            //第3个元素==第1个元素
            if (nums[pa - 2] != nums[pb]) {
                nums[pa] = nums[pb];
                pa++;
            }
            pb++;
        }
        return pa;
    }
}
