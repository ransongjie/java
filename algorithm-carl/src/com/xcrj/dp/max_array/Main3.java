package com.xcrj.dp.max_array;

/**
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 * 最长公共子数组，子数组需要连续
 * dp[i][j] 以nums1[i-1]结尾 以nums2[j-1]结尾
 */
public class Main3 {
    public int findLength(int[] nums1, int[] nums2) {
        // dp[i][j]=以nums1[i-1]结尾的子数组和以nums2[j-1]结尾的子数组 最长公共子数组
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 0; i < nums1.length + 1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < nums2.length + 1; i++) {
            dp[0][i] = 0;
        }
        dp[0][0] = 0;

        int max = 0;
        for (int i = 1; i < nums1.length + 1; i++) {
            //nums1中的字符i和nums2中的每个字符对比
            for (int j = 1; j < nums2.length + 1; j++) {
                /**
                 * nums[i-1],nums[i]
                 * nums[j-1],nums[j]
                 */
                if (nums1[i - 1] == nums2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
