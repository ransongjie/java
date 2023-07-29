package com.xcrj.offer_special.pass2;
/**
 * 剑指 Offer II 089. 房屋偷盗
 * 相邻的房间都被偷盗，会触发报警
 */
public class Solution89 {
    // 动态规划
    public int rob1(int[] nums) {
        if(null==nums) return 0;
        if(1==nums.length) return nums[0];
        if(2==nums.length) return Math.max(nums[0],nums[1]);

        // 记录偷n间房的最大收益
        int[]dp=new int[nums.length];
        
        // 考虑1间房
        dp[0]=nums[0];
        // 考虑2间房
        dp[1]=Math.max(nums[0],nums[1]);
        // 考虑多间房
        for(int i=2;i<nums.length;i++){
            // 不能偷盗相邻的房间
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }

        return dp[nums.length-1];
    }

    // 动态规划，滚动数组
    public int rob2(int[] nums) {
        if(null==nums) return 0;
        if(1==nums.length) return nums[0];
        if(2==nums.length) return Math.max(nums[0],nums[1]);
        
        int prev=nums[0];
        int curr=Math.max(nums[0],nums[1]);
        int next=0;
        for(int i=2;i<nums.length;i++){
            next=Math.max(prev+nums[i],curr);
            prev=curr;
            curr=next;
        }
        return curr;
    }
}
