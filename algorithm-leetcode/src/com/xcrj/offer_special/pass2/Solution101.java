package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 101. 分割等和子集
 * 给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。
 * 本题是"NP完全问题"，暂时没有多项式时间复杂度的解法，如果有则证明P=NP，图灵奖。
 * <p>
 * 这道题与传统的「0−1背包问题」的区别：
 * - weight sum <= bag availabe weight:传统的「0−1背包问题」要求选取的物品的重量之和“不超过”背包的总容量，
 * - weight sum = bag availabe weight/2:这道题则要求选取的数字的和“恰好等于”整个数组的元素和的一半
 * <p>
 * 输入，只包含正整数的非空数组 nums[]
 * 输出，true/false，选取的值之和=sum/2
 */
public class Solution101 {

    // xcrj 动态规划，状态数组，初始状态，状态转移
    public boolean canPartition1(int[] nums) {
        // 剔除不满足的情况
        //长度
        if(nums.length<2)return false;
        int sum=Arrays.stream(nums).sum();
        //奇数
        if(sum%2==1)return false;
        //最大值
        if(Arrays.stream(nums).max().getAsInt()>sum/2)return false;

        //动态规划数组。在[0:i]范围内取值=j
        boolean[][]dp=new boolean[nums.length][sum/2+1];

        //初始状态
        //在[0:i]范围内不取值=0
        for(int i=0;i<nums.length;i++)dp[i][0]=true;
        //在[0:0]范围内取值=nums[0], 取nums[]中的下标0的元素即可
        dp[0][nums[0]]=true;

        //状态转移
        for(int i=1;i<nums.length;i++){
            for(int j=1;j<=sum/2;j++){
                // 在[0:i]范围内取值=j，j<nums[i], 不能取nums[i], 看dp[i-1][j]的结果
                if(j<nums[i])dp[i][j]=dp[i-1][j];
                // 在[0:i-1]范围内取值=j-nums[i], 选取nums[i], 在[0:i]范围内取值=j
                else dp[i][j]=dp[i-1][j]|dp[i-1][j-nums[i]];
            }
        }

        return dp[nums.length-1][sum/2];
    }

    // 动态规划+滚动数组+倒序更新
    public boolean canPartition2(int[] nums) {
        //剔除不满足的情况
        if(nums.length<2)return false;
        int sum=Arrays.stream(nums).sum();
        if(sum%2==1)return false;
        if(Arrays.stream(nums).max().getAsInt()>sum/2)return false;

        //保留j，在[0:i]范围内取值=j
        boolean[] dp=new boolean[sum/2+1];

        //在[0:i]范围内不取值=0
        dp[0]=true;
        //在[0:0]范围内取值=nums[0], 取nums[]中的下标0的元素即可
        dp[nums[0]]=true;

        //状态转移
        for(int i=1;i<nums.length;i++){
            //xcrj 倒序更新，保证靠小下标后更新
            //j>=nums[i]
            // for(int j=sum/2;j>=1;j--){
            //     //if(j<nums[i])dp[i][j]=dp[i-1][j];
            //     //else dp[i][j]=dp[i-1][j]|dp[i-1][j-nums[i]];
            //     if(j<nums[i])dp[j]=dp[j];
            //     else dp[j]=dp[j]|dp[j-nums[i]];
            // }
            //等价于上面的for循环
            for(int j=sum/2;j>=nums[i];j--){
                dp[j]=dp[j]|dp[j-nums[i]];
            }
        }

        return dp[sum/2];
    }
}
