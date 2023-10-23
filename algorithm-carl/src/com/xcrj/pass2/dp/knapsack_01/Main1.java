package com.xcrj.pass2.dp.knapsack_01;

public class Main1 {
    /**
     * @param weight  物品重量
     * @param value   物品价值
     * @param bagSize 背包承载量
     */
    public static int testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        int n = weight.length;
        // dp数组
        // dp[i][j]=dp[0~i物品][背包承重]=最大价值
        int[][] dp = new int[n][bagSize + 1];
        // 初始状态
        // 背包承重为0，遍历物品
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        // 只有第1个物品
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[0][i] = value[0];
        }
        // 状态转移
        // 先物品，再承重
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < bagSize + 1; j++) {//承重至少是1
                // 物品i能放到背包j中吗
                if (weight[i] > j) {//一定放不下去
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 放入这件物品，不放入这件物品
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
        //dp数组
        int[] dp = new int[bagSize + 1];
        //初始状态
        //背包承重为0，遍历物品
        //只有第1件物品，遍历背包
        for (int i = weight[0]; i < bagSize + 1; i++) {
            dp[i] = value[0];
        }
        //状态转移
        //先物品，再背包
        for (int i = 1; i < n; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                // 不放入第i件物品，放入第i件物品
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
        }
        //结果
        return dp[bagSize];
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        int maxV = testWeightBagProblem2(weight, value, bagSize);
        System.out.println(maxV);
    }
}
