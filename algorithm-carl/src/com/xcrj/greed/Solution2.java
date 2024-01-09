package com.xcrj.greed;
/**
 * https://leetcode.cn/problems/maximum-subarray/
 * 最大子数组和
 */
public class Solution2{
    public int maxSubArray(int[] nums) {
        if(nums.length==1) return nums[0];
        int ans=Integer.MIN_VALUE;
        int cnt=0;
        for(int n:nums){
            cnt+=n;
            if(cnt>ans){
                ans=cnt;
            }
            // 并非遇到负数就cnt=0。如, 1 1 -1 4
            if(cnt<=0) cnt=0;
        }
        return ans;
    }
}