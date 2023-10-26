package com.xcrj.pass2.dp.knapsack_complete;

/**
 * https://leetcode.cn/problems/coin-change-ii/submissions/
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 */
public class Main2 {
    /**
     * 必须先物品再遍历背包承重
     * 反之，
     * 背包i中放入了硬币x,y
     * 背包j中也放入了硬币x,y 产生相同的组合
     */
    public int change(int amount, int[] coins) {
        int bagSize = amount;
        int[] weight = coins;
        int[] value = coins;
        int n = weight.length;
        //dp数组
        //dp[背包]=组合数
        int[] dp = new int[bagSize + 1];
        //初始状态
        dp[0] = 1;//dp[背包承重为0]=1个硬币也不放也是一种组合
        //状态转移
        //组合：for物品for背包
        for (int i = 0; i < n; i++) {
            for (int j = weight[i]; j < bagSize + 1; j++) {
                dp[j] += dp[j - weight[i]];//diff +=方法数
            }
        }
        //结果
        return dp[bagSize];
    }

    /**
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        // 问题转换
        int bagSize = amount;
        int[] weight = coins;
        int[] value = coins;
        int n = weight.length;
        // dp数组
        int[][] dp = new int[n][bagSize + 1];
        // 初始状态
        // 只有coins[0]无数个
        for (int i = 0; i < bagSize + 1; i += coins[0]) {
            //dp[0][0]=dp[只有第0个硬币无数个][背包承重为0]=1个硬币都不选也是1种方法
            //dp[0][coins[0]*k个]=只有1种方法选第0个硬币k个凑成当前coins[0]*k个
            dp[0][i] = 1;
        }
        // 状态转移
        // 先物品再背包
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < bagSize + 1; j++) {
                for (int k = 0; k * coins[i] <= j; k++) {
                    //选第i个硬币k个，+dp[前i-1个硬币][j-coins[i]*k承重]
                    dp[i][j] += dp[i - 1][j - k * coins[i]];
                }
            }
        }
        // 结果
        return dp[n - 1][bagSize];
    }
}
