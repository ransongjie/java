package com.xcrj.interview150.arr_str;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/rotate-array
 * 旋转数组
 */
public class Solution02 {
    public static void main(String[] args) {
        Solution02 s02 = new Solution02();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        s02.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public void rotate(int[] nums, int k) {
        if (k == 0) return;
        int n = nums.length;
        if (n == 1) return;
        k %= n;//移动n=不移动，移动n+1=移动1
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int s, int e) {
        int tmp;
        while (s < e) {
            tmp = nums[s];
            nums[s] = nums[e];
            nums[e] = tmp;
            s++;
            e--;
        }
    }
}
