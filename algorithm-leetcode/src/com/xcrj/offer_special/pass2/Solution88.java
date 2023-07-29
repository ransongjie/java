package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 088. 爬楼梯的最少成本
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
 * 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 */
public class Solution88 {

    // 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
    public int minCostClimbingStairs1(int[] cost) {
        // xcrj cost.length + 1
        int[] dp = new int[cost.length + 1];

        // 初始台阶，不需要体力花费
        dp[0] = dp[1] = 0;

        // xcrj <=
        for(int i=2;i<=cost.length;i++){
            // 倒退两个台阶而来
            // 倒退一个台阶而来
            dp[i]=Math.min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1]);
        }

        return dp[cost.length];
    }

    /**
     * 动态规划，滚动数组
     * 滚动数组：使用prev，curr，next交替记录值
     */
    public int minCostClimbingStairs2(int[] cost) {
        int prev=0,curr=0,next=0;
        for(int i=2;i<=cost.length;i++){
            next=Math.min(prev+cost[i-2],curr+cost[i-1]);
            prev=curr;
            curr=next;
        }

        return curr;
    }
}
