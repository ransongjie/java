package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 089. 房屋偷盗
 * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class Solution89 {
    /**
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     * 动态规划+动态规划数组
     */
    public int rob1(int[] nums) {
        if (null == nums) {
            return 0;
        }
        if (0 == nums.length) {
            return 0;
        }
        if (1 == nums.length) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        // 只有一间房,dp[0]=nums[0]
        dp[0] = nums[0];
        // 只有两间房,dp[1]=max{nums[0],nums[1]}
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 有多间房，dp[i]=max{dp[i-2]+nums[i],dp[i-1]},不能偷盗连续的两间房
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    /**
     * 动态规划+滚动数组
     * 滚动数组：使用prev，curr，next交替记录值
     */
    public int rob2(int[] nums) {
        if (null == nums) {
            return 0;
        }
        if (0 == nums.length) {
            return 0;
        }
        if (1 == nums.length) {
            return nums[0];
        }

        // >=两间房
        // prev 之前 总最大价值
        // 只有一间房,dp[0]=nums[0]
        int prev = nums[0];
        // curr 当前 总最大价值
        // 只有两间房,dp[1]=max{nums[0],nums[1]}
        int curr = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 有多间房，dp[i]=max{dp[i-2]+nums[i],dp[i-1]},不能偷盗连续的两间房
            int next = Math.max(prev + nums[i], curr);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
