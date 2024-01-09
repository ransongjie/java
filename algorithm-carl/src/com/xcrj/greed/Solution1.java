package com.xcrj.greed;
/**
 * https://leetcode.cn/problems/wiggle-subsequence/
 * 摆动序列
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 */
public class Solution1{
    public int wiggleMaxLength(int[] nums) {
        if(nums.length<=1) {return nums.length;}
        int ans=1;// 默认值，用于处理只有2个元素的摆动序列
        int curDiff=0;
        int preDiff=0;
        for(int i=1;i<nums.length;i++){
            curDiff=nums[i]-nums[i-1];
            // 凹||凸
            if((preDiff<=0&&curDiff>0)||(preDiff>=0&&curDiff<0)){
                ans++;
                // 发生摆动时更新前一个值，避免平台上升型造成错误
                preDiff=curDiff;
            }
        }
        return ans;
    }
}