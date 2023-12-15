package com.xcrj.pass2.dp.knapsack_01;

/**
 * https://leetcode.cn/problems/target-sum/submissions/
 * 494. 目标和
 * nums[]数组元素前添加+或-，再求和，求target
 */
public class Main4 {

    /**
     * 物品=nums元素
     * bagSize=left
     * weight=nums
     * value=nums
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // nums中元素全负或正，都不能凑成target
        if (Math.abs(target) > sum)
            return 0;
        // left(num前添加+元素之和)-(right添加-号元素之和)=target
        // left=(sum+target)/2 && left是正整数
        // (sum + target)必须是2的倍数，即偶数
        if (((sum + target) & 1) == 1)
            return 0;
        // 01背包问题
        int bagSize = Math.abs((target + sum) >> 1);
        int[] weight = nums;
        int n = weight.length;
        // dp数组
        // dp[i][j]=dp[0~i][背包承重j]=0~i物品凑成背包承重j的方法数
        int[][] dp = new int[n][bagSize + 1];
        // 初始状态
        // 背包为0，遍历物品
        int zeroNum = 0;
        for (int i = 0; i < n; i++) {
            /**
             * 物品重量只能是0，才能凑成背包承重为0
             * 重量为0的物品数量=zeroNum=0，还是有1种方法凑成背包0，即什么物品都不选择一种方法
             * 2^zeroNum由来, 举例：zeroNum=2, 4种方法=都不选，只选择a物品，只选择b物品，选择a和b物品
             */
            if (weight[i] == 0)
                zeroNum++;
            dp[i][0] = (int)Math.pow(2, zeroNum);
        }
        // 只有第1个物品，遍历背包
        for (int i = 0; i < bagSize + 1; i++) {
            // 只有1个物品怎么凑成背包i，只有当背包i=weight[0]，把这个物品放入背包中一种方法
            if (weight[0] == i)
                dp[0][i] = 1;
        }
        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < bagSize+1; j++) {
                // 第i个物品都放不下
                if(weight[i]>j){
                    dp[i][j]=dp[i-1][j];
                }else{
                    // 不放入第i个物品，放入第i个物品
                    dp[i][j]=dp[i-1][j]+dp[i-1][j-weight[i]];
                }
            }
        }
        // 结果
        return dp[n-1][bagSize];
    }

    /**
     * 求方法数
     * <p>
     * 问题转换
     * - 一定有 left组合 - right组合 = target, right可以为0
     * - left - (sum - left) = target 推导出 left = (target + sum)/2 ，装满容量为 left
     * 的背包，有几种方法
     * bagSize=left
     * weight=nums
     * value=nums
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // nums所有元素全添加-或+，都凑不出target
        if (Math.abs(target) > sum)
            return 0;
        // left=(target+sum)/2 && left是整数(left不能是小数)
        if (((target + sum) & 1) == 1)
            return 0;

        // 背包问题
        int bagSize = Math.abs((target + sum) >> 1);// 保证bagSize非负
        int[] weight = nums;
        int n = weight.length;
        // dp数组
        int[] dp = new int[bagSize + 1];
        // 初始状态
        // 背包承重为0=什么都不放也是一种方法
        dp[0] = 1;
        // 状态转移
        for (int i = 0; i < n; i++) {// i=0
            for (int j = bagSize; j >= weight[i]; j--) {
                // 方法数
                dp[j] += dp[j - weight[i]];
            }
        }
        // 结果
        return dp[bagSize];
    }
}
