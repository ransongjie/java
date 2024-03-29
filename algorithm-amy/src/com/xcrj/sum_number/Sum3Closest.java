package com.xcrj.sum_number;

import java.util.Arrays;

/**
 * 双指针
 * 1个for+2个指针
 * https://leetcode.cn/problems/3sum-closest/
 * 与target最接近的三数之和
 */
public class Sum3Closest {
    /**
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];//可能的结果
        for (int i = 0; i < nums.length; i++) {
            int s = i + 1;
            int e = nums.length - 1;
            while (s < e) {
                int sum = nums[i] + nums[s] + nums[e];//实际的结果
                if (Math.abs(target - sum) < Math.abs(target - ans)) {//可能结果 vs. 实际结果
                    ans = sum;
                }
                if (target > sum) {//sum
                    s++;
                } else if (target < sum) {//s++, ans将更大
                    e--;
                } else {
                    return sum;
                }
            }
        }
        return ans;
    }
}
