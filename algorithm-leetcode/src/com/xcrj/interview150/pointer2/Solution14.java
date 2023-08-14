package com.xcrj.interview150.pointer2;

/**
 * https://leetcode.cn/problems/container-with-most-water
 * 盛最多水的容器
 * 不是二维接雨水
 */
public class Solution14 {
    public int maxArea(int[] heights) {
        int i = 0, j = heights.length - 1, res = 0, width;
        while (i < j) {
            //木桶效应
            width = j - i;
            if (heights[i] <= heights[j]) {
                res = Math.max(res, width * heights[i]);
                i++;
            } else {
                res = Math.max(res, width * heights[j]);
                j--;
            }
        }
        return res;
    }
}
