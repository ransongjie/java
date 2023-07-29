package com.xcrj.offer_special.pass3;

/**
 * 剑指 Offer II 009. 乘积小于k的最短连续子数组的数量
 * - 数组元素乘积小于k
 * - 数组元素连续
 * - 有多少个这样的子数组
 */

public class Solution9{

    /**
     * 乘积小于k的最短连续子数组的数量
     * 两个while循环夹击
     * - 先j从0到len 探求满足 p>=k 的解
     * - 再i从0到j   探求满足 最短 p<k 的解
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int r=0;
        int i=0;
        int j=0;
        // 乘法从1开始
        int p=1;
        while(j<nums.length){
            p*=nums[j];
            // xcrj 先找不满足的边界条件
            // i可以等于j
            while(i<=j&&p>=k){
                p/=nums[i];
                i++;
            }
            // 满足 p<k 的连续子数组,连续子数组的子数组也满足 p<k
            r+=j-i+1;
            j++;
        }

        return r;
    }
}