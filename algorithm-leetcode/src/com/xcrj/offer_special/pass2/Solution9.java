package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 009. 乘积小于k的最短连续子数组的数量
 * - 数组元素乘积小于k
 * - 数组元素连续
 * - 有多少个这样的子数组
 */
public class Solution9 {
    /**
     * 连续最短子序列
     * 滑动窗口(双指针) 同向滑动
     * - i j开始都指向0
     * - 2个while循环夹击
     * while(j<len){
     *   while(i<=j&&条件){
     *     
     *     i++;
     *   }
     *   j++;
     * }
     * 
     * 乘积小于k的最短连续子数组的数量
     * - 从i到j的子数组的子数组都满足乘积小于k
     * 
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int r = 0;
        // xcrj 乘法从1开始
        int p = 1;
        while (j < nums.length) {
            p *= nums[j];
            while (i <= j && p >= k) {
                p /= nums[i];
                i++;
            }
            r += j - i + 1;
            j++;
        }
        return r;
    }
}
