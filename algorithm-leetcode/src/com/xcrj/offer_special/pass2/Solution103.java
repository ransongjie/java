package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 103. 最少的硬币数目
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 硬币面值至少为1
 * 输入，coins[]硬币面值，amount 总金额，硬币数量无限
 * 输出，组成总金额的最少硬币个数
 */
public class Solution103 {
    /**
     * 记忆搜索算法
     * - 记忆，为了避免重复计算使用 count[金额-1]记录组成金额所需的最少硬币数量。下次直接取用
     * - 搜索，F(S)=F(S-C)+1，C是最后1枚硬币的币值，遍历最后一枚硬币的币值
     */
    public int coinChange1(int[] coins, int amount) {
        //硬币面值至少为1，拿不出硬币
        if(amount<1)return 0;

        return F(coins, amount, new int[amount]);
    }

    /**
     * 求memory[金额]=组成金额所需的最少硬币数量, 数组从0开始。
     * @param remain 剩余总金额
     * @param memory 记忆数组，记忆子问题的结果
     */
    private int F(int[] coins, int remain, int[] memory) {
        //无效总金额
        if(remain<0)return -1;
        //不取硬币
        if(remain==0)return 0;

        //问题结果已经存在
        if(memory[remain-1]!=0)return memory[remain-1];

        //深度，先达求解最小子问题。
        //xcrj 从最小子问题开始到最大问题，每个问题都遍历了所有币值
        //xcrj 先获取了子问题的组成金额的最小硬币数量，以此获取大问题的组成金额的最小硬币数量
        int minNum=Integer.MAX_VALUE;
        for(int coin:coins){
            int minSonNum=F(coins, remain-coin, memory);
            if(minSonNum>=0&&minSonNum<minNum)minNum=minSonNum+1;
        }

        memory[remain-1]=minNum==Integer.MAX_VALUE?-1:minNum;
        return memory[remain-1];
    }

    //动态规划
    //子问题，遍历所有币值情况下，获得组成剩余金额的最少硬币数量，剩余金额=源金额-每种币值
    public int coinChange2(int[] coins, int amount) {
        //dp[金额]=最少硬币数量
        int[] dp = new int[amount + 1];
        // Arrays.fill(dp, Integer.MAX_VALUE); dp[i-coins[j]]+1后溢出
        Arrays.fill(dp, amount + 1);
        
        //边界状态，dp[0]=0
        dp[0]=0;

        //状态转移
        for(int i=0;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                //dp[剩余金额]=min(dp[剩余金额],dp[剩余金额-每种币值]+1)
                if(coins[j]<=i)dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
            }
        }

        return dp[amount]==amount+1?-1:dp[amount];
    }
}
