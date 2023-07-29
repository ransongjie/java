package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 101. 分割等和子集
 * 给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。
 * 本题是"NP完全问题"，暂时没有多项式时间复杂度的解法，如果有则证明P=NP，图灵奖。
 * <p>
 * 问题转换：
 * - 给定一个只包含正整数的非空数组nums[0]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半
 * - 这个问题可以转换成「0−1背包问题」
 * <p>
 * 这道题与传统的「0−1背包问题」的区别：
 * - 传统的「0−1背包问题」要求选取的物品的重量之和“不超过”背包的总容量，
 * - 这道题则要求选取的数字的和“恰好等于”整个数组的元素和的一半
 * <p>
 * 分析
 * - sum/2=target
 */
public class Solution101 {
    /**
     * 分阶段
     * - 把nums[0],nums[0:1],...,nums[0,n]
     * - 把sum/2=target,从j=0,j=1,...,j=target
     * <p>
     * 特殊情况处理
     * - 数组长度len<2，不可能将数组分割成元素和相等的两个子集，直接返回false
     * - 数组长度len>2，数组元素和sum/2是奇数，不可能将数组分割成元素和相等的两个子集，直接返回false
     * - 数组元素和sum/2是偶数，数组长度len>2，数组中最大值max>target=sum/2，则剩余元素和<target,不可能将数组分割成元素和相等的两个子集，直接返回false
     * <p>
     * 动态规划数组
     * - dp[i][j]=sum(nums[0:i]中选若干元素)==j，j=0则选取0个
     * - dp[i][j],行下标代表元素选取范围，列下标代表要求元素和
     * <p>
     * 边界条件处理
     * - 当j=0时，dp[i][0]=true
     * - 当i=0时，dp[0][nums[0]]=true，只能选取nums[0]这个元素
     * <p>
     * 状态转移方程
     * - 当i>0&&j>0时，若j<nums[i]，则不能选nums[i]，则dp[i][j]=dp[i-1][j]
     * - 当i>0&&j>0时，若j>nums[i]，若选取nums[i]，则dp[i][j]=dp[i-1][j-nums[i]]，即看看sum(nums[0:i-1]中选若干元素)==j-nums[i],若存在,则sum(nums[0:i]也可以选取这几个元素+nums[i])=j
     * - 当i>0&&j>0时，若j>nums[i]，若不选取nums[i]，则dp[i][j]=dp[i-1][j]，即sum(nums[0:i-1]中选若干元素)==j，若存在，则sum(nums[0:i]也可以选取这几个元素)=j
     */
    public boolean canPartition1(int[] nums) {
        // 特殊情况处理
        // 数组长度len<2，不可能将数组分割成元素和相等的两个子集，直接返回false
        if (nums.length < 2) return false;
        // 数组长度len>2，数组元素和sum/2是奇数，不可能将数组分割成元素和相等的两个子集，直接返回false
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        // 数组元素和sum/2是偶数，数组长度len>2，数组中最大值max>target=sum/2，则剩余元素和<target,不可能将数组分割成元素和相等的两个子集，直接返回false
        if (Arrays.stream(nums).max().getAsInt() > sum / 2) return false;

        /**
         * 动态规划数组
         * - dp[i][j]=sum(nums[0:i]中选若干元素)==j，j=0则选取0个
         * - dp[i][j],行下标代表元素选取范围，列下标代表要求元素和
         */
        boolean[][] dp = new boolean[nums.length][sum / 2 + 1];

        // 边界条件处理
        // 当j=0时，dp[i][0]=true
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        // 当i=0时，dp[0][nums[0]]=true，只能选取nums[0]这个元素
        dp[0][nums[0]] = true;

        // 状态转移方程
        // i=0已经处理过，dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            // j=0已经处理过，dp[i][0] = true;
            for (int j = 1; j <= sum / 2; j++) {
                // 当i>0&&j>0时，若j<nums[i]，则不能选nums[i]，则dp[i][j]=dp[i-1][j]
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 当i>0&&j>0时，若j>nums[i]，若选取nums[i]，则dp[i][j]=dp[i-1][j-nums[i]]，即看看sum(nums[0:i-1]中选若干元素)==j-nums[i],若存在,则sum(nums[0:i]也可以选取这几个元素+nums[i])=j
                // 当i>0&&j>0时，若j>nums[i]，若不选取nums[i]，则dp[i][j]=dp[i-1][j]，即sum(nums[0:i-1]中选若干元素)==j，若存在，则sum(nums[0:i]也可以选取这几个元素)=j
                else {
                    // 按位或，只要其中一个为true，则结果为true
                    dp[i][j] = dp[i - 1][j - nums[i]] | dp[i - 1][j];
                }
            }
        }

        return dp[nums.length - 1][sum / 2];
    }

    /**
     * 动态规划数组+空间压缩+倒序
     * 动态规划数组
     * - dp[i][j]=sum(nums[0:i]中选若干元素)==j，j=0则选取0个
     * - dp[i][j],行下标代表元素选取范围，列下标代表要求元素和
     * - dp[j]=sum(nums[0:当前i]中选若干元素)==j，j=0则选取0个
     * - dp[j] =dp[j] | dp[j - num],等号右边dp[j]=sum(nums[0:前一个i]中选若干元素)==j
     * - 当前行dp[j]=前一行dp[j] | 前一行dp[j - num]
     * <p>
     * 倒序原因：
     * - 若正序更新dp，在计算dp[j]值的时候，dp[j−nums[i]]已经被更新，不再是上一行的dp值。
     */
    public boolean canPartition2(int[] nums) {
        // 特殊情况处理
        // 数组长度len<2，不可能将数组分割成元素和相等的两个子集，直接返回false
        if (nums.length < 2) return false;
        // 数组长度len>2，数组元素和sum/2是奇数，不可能将数组分割成元素和相等的两个子集，直接返回false
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        // 数组元素和sum/2是偶数，数组长度len>2，数组中最大值max>target=sum/2，则剩余元素和<target,不可能将数组分割成元素和相等的两个子集，直接返回false
        if (Arrays.stream(nums).max().getAsInt() > sum / 2) return false;

        /**
         * 动态规划数组
         * - dp[i][j]=sum(nums[0:i]中选若干元素)==j，j=0则选取0个
         * - dp[i][j],行下标代表元素选取范围，列下标代表要求元素和
         * - dp[j]=sum(nums[0:当前i]中选若干元素)==j，j=0则选取0个
         * - dp[j] =dp[j] | dp[j - nums[i]],等号右边dp[j]=sum(nums[0:前一个i]中选若干元素)==j
         */
        boolean[] dp = new boolean[sum / 2 + 1];

        // 边界条件处理
        // 当j=0时，dp[i][0]=true
        dp[0] = true;
        // i=0已经处理过，dp[0][nums[0]] = true;
        dp[nums[0]] = true;

        // 状态转移方程
        for (int i = 1; i < nums.length; i++) {
            /**
             * 上面的方法j是1~sum/2。这里是sum/2~nums[i]
             * j>=nums[i]原因：
             * - 当i>0&&j>0时，若j<nums[i]，则不能选nums[i]，则dp[i][j]=dp[i-1][j]
             * - dp[j]=dp[j], 所以下面只处理>=nums[i]的j
             */

            /**
             *  当i>0&&j>0时，若j>nums[i]，若选取nums[i]，则dp[i][j]=dp[i-1][j-nums[i]]，即看看sum(nums[0:i-1]中选若干元素)==j-nums[i],若存在,则sum(nums[0:i]也可以选取这几个元素+nums[i])=j
             *  当i>0&&j>0时，若j>nums[i]，若不选取nums[i]，则dp[i][j]=dp[i-1][j]，即sum(nums[0:i-1]中选若干元素)==j，若存在，则sum(nums[0:i]也可以选取这几个元素)=j
             *  dp[j] =dp[j] | dp[j - nums[i]],等号右边dp[j]=sum(nums[0:前一个i]中选若干元素)==j
             *  为什么倒序：
             *  - 若正序更新dp，在计算dp[j]值的时候，dp[j−nums[i]]已经被更新，不再是上一行的dp值。
             *
             *  若正序
             *  - dp[j−nums[i]]=dp[j−nums[i]] | dp[j-nums[i]-nums[i]]
             *  - dp[j] = dp[j] | dp[j - nums[i]]; dp[j - nums[i]]已经被更新
             */
            for (int j = sum / 2; j >= nums[i]; j--) {
                dp[j] = dp[j] | dp[j - nums[i]];
            }
        }

        return dp[sum / 2];
    }
}
