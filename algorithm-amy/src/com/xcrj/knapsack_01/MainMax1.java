package com.xcrj.knapsack_01;

public class MainMax1 {
    /**
     * @param weight  物品重量
     * @param value   物品价值
     * @param bagSize 背包承载量
     */
    public static int testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        //dp数组
        int[][] dp = new int[weight.length][bagSize + 1];//dp[i件物品][承重]=最大价值
        //初始状态
        for (int i = 0; i < weight.length; i++) {
            dp[i][0] = 0;//背包承重为0，一件物品也放不进去，最大价值为0
        }
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[0][i] = value[0];//仅有第0件物品，背包承重高于此物品，最大价值就是第0件物品的价值
        }
        //状态转移
        for (int i = 1; i < weight.length; i++) {//先物品再承重
            for (int j = 1; j < bagSize + 1; j++) {
                if (weight[i] > j) {//背包j，放不下第i件物品
                    dp[i][j] = dp[i - 1][j];
                } else {//背包j，放入或者不放入第i件物品
                    dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);
                }
            }
        }
        //结果
        return dp[weight.length - 1][bagSize];//dp[n件物品][bagSize承重]=最大价值
    }

    /**
     * dp数组的下一行只依赖上一行，进行空间压缩
     * @param weight  物品重量
     * @param value   物品价值
     * @param bagSize 背包承载量
     */
    public static int testWeightBagProblem2(int[] weight, int[] value, int bagSize) {
        //dp数组
        int[] dp = new int[bagSize + 1];
        //初始状态
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[i] = value[0];
        }
        //状态转移
        for (int i = 1; i < weight.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
        }
        //结果
        return dp[bagSize];
    }
}
