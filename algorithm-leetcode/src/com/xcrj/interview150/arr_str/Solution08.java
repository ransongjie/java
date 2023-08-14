package com.xcrj.interview150.arr_str;

/**
 * https://leetcode.cn/problems/product-of-array-except-self/solutions/272369
 * 除自身以外数组的乘积
 */
public class Solution08 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] rs = new int[n];
        //求所有i左侧所有元素的乘积
        rs[0] = 1;//i=0左侧没有元素
        for (int i = 1; i < n; i++) {
            //0~i-1元素的乘积=0~i-2元素乘积*nums[i-1]
            rs[i] = rs[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        //求所有i右侧所有元素的乘积
        for (int i = n - 1; i >= 0; i--) {
            rs[i] *= suffix;
            suffix *= nums[i];
        }

        return rs;
    }
}
