package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 008. 和大于等于target的连续最短子数组
 * - 元素和>=target
 * - 元素构成的子序列是连续的
 * - 子序列的长度最短
 */
public class Solution8 {
    /**
     * 滑动窗口(双指针) 同向滑动
     * - i j开始都指向0
     * - 先while移动j 探求满足要求
     * - 再while移动i 探求不满足要求
     * - 先满足要求，再不满足要求，夹击求最短连续子序列
     * @return 没找到满足sum>=target的连续子序列 返回0
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j];
            while (sum >= target) {
                minLen = Math.min(minLen, j - i + 1);
                sum -= nums[i];
                // 再移动i 探求sum<target
                i++;
            }
            // 先移动j 探求sum>=target
            j++;
        }
        // 没找到满足sum>=target的连续子序列 返回0
        return Integer.MAX_VALUE==minLen?0:minLen;
    }

}
