package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 103. 最少的硬币数目
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 */
public class Solution103 {

    /**
     * 记忆搜索算法
     * - 记忆，为了避免重复计算使用memory[金额-1]记录组成金额所需的最少硬币数量。下次直接取用
     * - 搜索，F(S)=F(S-C)+1，C是最后1枚硬币的币值，遍历最后一枚硬币的币值
     */
    public int coinChange1(int[] coins, int amount) {
        // 已知硬币的面值至少为1，只能选择0个硬币
        if (amount < 1) {
            return 0;
        }

        // 初始，剩余总金额为amount，记忆组成金额为amount的所需最少硬币数量
        return F(coins, amount, new int[amount]);
    }

    /**
     * 为避免重复计算，将每个子问题的答案记忆到memory[]中
     * memory[金额-1]=组成金额所需的最少硬币数量。下一个金额=remain-coin
     * memory[rem-1]，-1，数组从0开始
     *
     * @param remain 剩余总金额
     * @param memory 记忆数组，记忆子问题的结果
     */
    private int F(int[] coins, int remain, int[] memory) {
        // 总金额<0, 没有任何一种硬币组合能=rem，返回-1
        if (remain < 0) {
            return -1;
        }

        // 总金额=0，只能取0个硬币，币值总和才等于0
        if (remain == 0) {
            return 0;
        }

        // memory[rem - 1] 子问题的答案已经存在，直接返回
        if (memory[remain - 1] != 0) {
            return memory[remain - 1];
        }

        // memory[rem - 1] 子问题的答案已经存在，计算返回
        // F(S)=F(S-C)+1，C是最后1枚硬币的币值，遍历最后一枚硬币的币值
        int minNum = Integer.MAX_VALUE;
        for (int coin : coins) {
            int fsc = F(coins, remain - coin, memory);
            // 0<=S-ci<minNum
            if (fsc >= 0 && fsc < minNum) {
                minNum = fsc + 1;
            }
        }

        // 是否有能达到总金额的硬币数量
        memory[remain - 1] = minNum == Integer.MAX_VALUE ? -1 : minNum;
        return memory[remain - 1];
    }

    /**
     * 动态规划
     * 动态规划数组：F(i)=min{F(i-cj)}+1, i代表总金额，cj代表最组成i的最后1个硬币，j从0到n-1，遍历每个硬币。
     */
    public int coinChange2(int[] coins, int amount) {
        // 动态规划数组：F(i)=min{F(i-cj)}+1, i代表总金额，cj代表最组成i的最后1个硬币，j从0到n-1，遍历每个硬币。
        int[] dp = new int[amount + 1];
        // 初始化动态规划数组
        // amount+1,已知硬币币值最小为1,硬币总数不可能超过总金额+1,因为后面存在Math.min比较
        Arrays.fill(dp, amount + 1);

        // 边界条件，dp[总金额为0]=选择0个硬币
        dp[0] = 0;
        // 遍历总金额
        for (int i = 0; i <= amount; i++) {
            // 遍历cj，组成总金额i的最后1个硬币
            for (int j = 0; j < coins.length; j++) {
                // 第j个硬币的币值不能大于总金额值
                if (coins[j] <= i) {
                    // F(i)=min{F(i-cj)}+1
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        // >amount，就是=amount + 1，不存在能够组成amount的硬币组合
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
