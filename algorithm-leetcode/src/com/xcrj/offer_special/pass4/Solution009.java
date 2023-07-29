package com.xcrj.offer_special.pass4;

/**
 * 乘积小于 K 的子数组
 * 乘积小于 K 的子数组的数量
 */
public class Solution009 {
    /**
     * 窗口
     * 先满足要求，再破坏要求，以达到最低要求
     * 先满足乘积<K，再让乘积>=K，以达到最低要求
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0, j = 0, n = nums.length, mul = 1, ans = 0;
        while (j < n) {
            mul *= nums[j];
            while (i <= j && mul >= k) {
                mul /= nums[i];
                i++;
            }
            ans += j - i + 1;//i~j之内的元素的乘积都小于K
            j++;
        }
        return ans;
    }
}
