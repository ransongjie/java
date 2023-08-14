package com.xcrj.interview150.pointer2;

/**
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted
 * 两数之和 II - 输入有序数组
 */
public class Solution13 {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1, sum;
        while (i < j) {
            sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};//从1开始
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{-1, -1};
    }
}
