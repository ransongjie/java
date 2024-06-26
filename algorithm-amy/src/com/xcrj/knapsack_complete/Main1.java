package com.xcrj.knapsack_complete;

/**
 * 完全背包问题 vs. 0-1背包问题。区别，每个物品都有无限个
 * 先遍历物品再遍历背包承重，先遍历背包承重再遍历物品 都可以
 * 完全背包问题在实际应用中，遍历的顺序才是最重要的
 * <p>
 * 实际问题：
 * 背包最大承重，物品重量，物品价值
 * 遍历顺序（求组合/排列）
 * 求总数，求最大/最小方法数
 */
public class Main1 {
    public static void main(String[] args) {
        int bagSize = 4;
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};

        f(bagSize, weight, value);
        g(bagSize, weight, value);
    }

    /**
     * for物品for背包，可求组合问题
     *
     * @param bagSize
     * @param weight
     * @param value
     */
    static void f(int bagSize, int[] weight, int[] value) {
        //dp数组
        int[] dp = new int[bagSize + 1];//dp[i件物品]=最大价值
        //初始状态
        //状态转移
        for (int i = 0; i < weight.length; i++) {
            for (int j = weight[i]; j <= bagSize; j++) {
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
        }
        //结果
        int max = Integer.MIN_VALUE;
        for (int n :
                dp) {
            max = Math.max(max, n);
        }
        System.out.println(max);
    }

    /**
     * for背包for物品，可求排列问题
     *
     * @param bagSize
     * @param weight
     * @param value
     */
    static void g(int bagSize, int[] weight, int[] value) {
        //dp数组
        int[] dp = new int[bagSize + 1];
        //初始状态
        //状态转移
        for (int i = 1; i < bagSize + 1; i++) {//i=1
            for (int j = 0; j < weight.length; j++) {
                if (i >= weight[j]) {
                    dp[i] = Math.max(dp[i], value[j] + dp[i - weight[j]]);
                }
            }
        }
        //结果
        int max = Integer.MIN_VALUE;
        for (int n :
                dp) {
            max = Math.max(max, n);
        }
        System.out.println(max);
    }
}
