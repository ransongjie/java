package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/jump-game/
 * 跳跃游戏
 * 贪心策略：
 * 局部最优解：每次取最大覆盖范围，整体最优解：最后得到整体最大覆盖范围，看是否能到终点。
 */
public class Solution5{

    public boolean canJump(int[] nums) {
        if(nums.length==1) return true;
        int cover=0;
        for(int i=0;i<=cover;i++){
            cover=Math.max(i+nums[i],cover);
            if(cover>=nums.length-1) return true;
        }
        return false;
    }
}