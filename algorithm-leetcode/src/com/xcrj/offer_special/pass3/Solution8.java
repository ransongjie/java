package com.xcrj.offer_special.pass3;

/**
 * 剑指 Offer II 008. 和大于等于target的连续最短子数组
 * - 元素和>=target
 * - 元素构成的子序列是连续的
 * - 子序列的长度最短
 */
public class Solution8 {

    /**
     * 双指针同向移动
     * 两个while循环夹击出最短子数组
     * - 先j从0开始往右移动，以满足sum>=target
     * - 在i从0开始往右移动，以满足长度最短的 sum>=target
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i=0;
        int j=0;
        int sum=0;
        int r=Integer.MAX_VALUE;
        while(j<nums.length){
            sum+=nums[j];
            while(sum>=target){
                r=Math.min(r, j-i+1);
                sum-=nums[i];
                i++;
            }
            j++;
        }

        return r==Integer.MAX_VALUE?0:r;
    }
}
