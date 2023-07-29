package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 009. 乘积小于 K 的子数组
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 * （数组元素乘积小于k&&数组元素连续）有多少个这样的数组
 */
public class Solution9 {
    /**
     * 滑动窗口
     * ！！！使用不同的指针达到不同的目的
     * i j开始都指向0
     * j后移直到product>=s
     * i后移直到product<s
     * sum+=j-i+1
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int product = 1;
        int sum = 0;
        while (j < nums.length) {
            product *= nums[j];
            // 输入[1,2,3] 0时，product一直为0,0/任何数=0
            // i<j避免i一直加，i==j子数组长度为1满足<k也可以
            while (i <= j && product >= k) {
                product /= nums[i];
                i++;
            }
            sum += j - i + 1;
            j++;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        System.out.println(solution9.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }
}
