package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/jump-game-ii/
 * 跳跃游戏II
 * 贪心策略：
 * 移动下标达到了当前覆盖的最远距离下标时，步数就要加一，来增加覆盖距离。最后的步数就是最少步数。
 */
public class Solution6{
    /*
     * 尽量少走，遍历到当前能覆盖的最大范围后，必须要多走1步，在覆盖范围内找1步走到新的最大覆盖
     */
    public int jump(int[] nums) {
        //直接就在终点，不需要走
        if(nums.length==1) return 0;
        int ans=0;//步数
        int cur=0;//当前能覆盖的范围
        int max=0;//最大能覆盖的范围
        for(int i=0;i<nums.length;i++){
            max=Math.max(i+nums[i],max);
            //遍历到当前能覆盖的最大范围，必须要多走1步
            if(i==cur){
                ans++;
                cur=max;
                if(max>=nums.length-1){
                    return ans;
                }
            }
        }
        return ans;
    }
}