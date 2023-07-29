package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 012. 左右两边子数组的和相等
 * 给你一个整数数组nums ，请计算数组的中心下标。
 * 数组中心下标是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 注意，数组中心元素不算在左侧或右侧
 * <p>
 * 如果中心下标位于数组最左端，那么左侧数之和视为0，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 * 如果数组有多个中心下标，应该返回最靠近左边的那一个。
 * 如果数组不存在中心下标，返回-1。
 */
public class Solution12 {
    /**
     * 前缀和
     * preSum[j]+k=preSum[i]，现在i=nums.len-1，k==preSum[j]
     */
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        // 前缀和中不包含任何元素时为0
        int preSum = 0;
        for (int j = 0; j < nums.length; j++) {
            // 下标j 数组中心元素不算在左侧或右侧
            if (total - nums[j] == 2 * preSum) {
                return j;
            }
            preSum += nums[j];
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution12 solution12 = new Solution12();
        System.out.println(solution12.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }
}
