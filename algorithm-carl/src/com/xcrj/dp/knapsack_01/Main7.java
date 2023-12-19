package com.xcrj.dp.knapsack_01;

/**
 * 背包理论
 * 01背包问题，每个物品只有1个&&只有选或不选01两种状态&&每个物品只能选1次
 */
public class Main7 {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int maxV = testWeightBagProblem2(weight, value, bagSize);
        System.out.println(maxV);
    }

    /**
     * @param weight  物品重量
     * @param value   物品价值
     * @param bagSize 背包承载量
     */
    public static int testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        int n = weight.length;// 物品数量
        // dp数组 dp[n-1][bagSize]=背包大小为bagSize操作第n件物品时的最大价值
        // dp[i][j]=背包大小为j操作第i+1件物品时的最大价值
        int[][] dp = new int[n][bagSize + 1];// 可以1个物品也不选, 背包重量可以为0
        // 初始状态
        // 背包大小为0
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        // 只有第0件物品
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[0][i] = value[0];
        }
        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < bagSize + 1; j++) {
                // 装不下第i件物品
                if (weight[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 装入第i件物品，不装入第i件物品
                    dp[i][j] = Math.max(value[i] + dp[i - 1][j - weight[i]], dp[i - 1][j]);
                }
            }
        }
        // 结果
        return dp[n - 1][bagSize];
    }

    /**
     * 压缩
     *
     * @param weight  物品重量
     * @param value   物品价值
     * @param bagSize 背包承载量
     */
    public static int testWeightBagProblem2(int[] weight, int[] value, int bagSize) {
        int n = weight.length;
        // dp数组
        int[] dp = new int[bagSize + 1];
        // 初始状态，只有第0件物品
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[i] = value[0];
        }
        // 状态转移。先物品再背包
        for (int i = 1; i < n; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {// 倒序
                dp[j] = Math.max(value[i] + dp[j - weight[i]], dp[j]);
            }
        }
        // 结果
        return dp[bagSize];
    }
}
