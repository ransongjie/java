package com.xcrj.trap_rain;

/**
 * https://leetcode.cn/problems/trapping-rain-water/
 * 一维接雨水
 */
public class Dimension1 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int ans = 0;
        //木桶边缘，左边，右边
        int lMax = height[0], rMax = height[height.length - 1];
        int l = 1, r = height.length - 2;
        while (l <= r) {
            //木桶效应，矮木板接水
            if (lMax <= rMax) {
                ans += Math.max(0, lMax - height[l]);//相邻木板高度差可以接水
                lMax = Math.max(lMax, height[l++]);
            } else {
                ans += Math.max(0, rMax - height[r]);
                rMax = Math.max(rMax, height[r--]);
            }
        }
        return ans;
    }
}
