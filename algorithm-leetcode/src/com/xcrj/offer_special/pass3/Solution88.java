package com.xcrj.offer_special.pass3;
/**
 * 剑指 Offer II 088. 爬楼梯的最少成本
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
 * 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 */
public class Solution88 {
    /**
     * 动态规划，分阶段，前面的阶段值可以推导后面的阶段值
     * dp[i]=min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])
     * 达到第i个阶梯的体力划分 取决于 到达第i-1个阶梯的体力划分 和 达到第i-2个阶梯的体力花费的最小值
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        //+1 到达第n个阶梯的体力花费 没有计算第n个阶梯的体力 因为还没有爬这个阶梯
        int[] dp=new int[cost.length+1];
        dp[0]=dp[1]=0;
        //
        for(int i=2;i<=cost.length;i++){
            dp[i]=Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }

    /**
     * 优化
     * 滚动数组减少动态规划数组空间占用
     * 只考虑3个阶梯
     * @param cost
     * @return
     */
    public int minCostClimbingStairs1(int[] cost) {
        int pre=0; // 第0个阶梯
        int cur=0; // 第1个阶梯
        int nxt;
        for(int i=2;i<=cost.length;i++){
            // 第2个阶梯
            nxt=Math.min(cur+cost[i-1],pre+cost[i-2]);
            pre=cur;
            cur=nxt;
        }
        return cur;
    }
}
