package com.xcrj.dp.knapsack_complete;

/**
 * 完全背包问题 vs. 0-1背包问题。区别，每个物品都有无限个
 * 先遍历物品再遍历背包承重，先遍历背包承重再遍历物品 都可以
 * 完全背包问题在实际应用中，遍历的顺序才是最重要的
 *
 * 实际问题：
 * 背包最大承重，物品重量，物品价值
 * 遍历顺序（求组合/排列）
 * 求总数，求最大/最小方法数
 */
public class Main1 {
    public static void main(String[] args) {
        f();
        g();
    }

    /**
     * 先for物品再for背包承重
     * 第i件物品会尝试放到每种承重的背包中
     */
    private static void f() {
        int[] weights = { 1, 3, 4 };
        int[] values = { 15, 20, 30 };
        int bagSize = 4;
        // 动态规划数组 dp[bagSize]=最大价值
        int[] dp = new int[bagSize + 1];
        // 初始化
        // 状态转移
        for (int i = 0; i < weights.length; i++) {
            // 背包的可行初始承重至少是weights[i]
            for (int j = weights[i]; j < bagSize + 1; j++) {
                // 不选择物品i 或 选择物品i
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        // 结果
        int max = Integer.MIN_VALUE;
        for (int a : dp) {
            max = Math.max(max, a);
        }
        System.out.println(max);
    }

    /**
     * 先for背包承重再for物品
     * i承重量的背包会尝试放入每个物品
     */
    private static void g() {
        int[] weights = { 1, 3, 4 };
        int[] values = { 15, 20, 30 };
        int bagSize = 4;
        // 动态规划数组 dp[bagSize]=最大价值
        int[] dp = new int[bagSize + 1];
        // 初始化
        // 状态转移
        for (int i = 1; i <= bagSize; i++) {
            for (int j = 0; j < weights.length; j++) {
                if (i - weights[j] >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - weights[j]] + values[j]);
                }
            }
        }
        // 结果
        int max = Integer.MIN_VALUE;
        for (int a : dp) {
            max = Math.max(max, a);
        }
        System.out.println(max);
    }
}
