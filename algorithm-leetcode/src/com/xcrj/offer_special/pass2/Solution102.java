package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 102. 加减的目标值方案数目
 * 输入，一个正整数数组 nums 和一个整数 target 
 * 向数组中的每个整数前添加 '+' 或 '-' 构造一个 表达式
 * 输出，正数和+负数和= target 的不同表达式的数目
 */
public class Solution102 {
    int count=0;
    //回溯法，在深度优先的过程中进行回溯，选择/不选择
    public int findTargetSumWays1(int[] nums, int target) {
        backTrack(nums, target, 0, 0);
        return count;
    }

    private void backTrack(int[] nums, int target, int idx, int sum) {
        if(nums.length==idx){
            if(sum==target)count++;
            //选取完所有元素就return
            return;
        }
        if(idx<nums.length){
            // 深度添加+号，nums[idx]前添加+号
            backTrack(nums, target, idx+1, sum+nums[idx]);
            // 回溯添加-号，nums[idx]前添加-号
            backTrack(nums, target, idx+1, sum-nums[idx]);
            return;
        }
    }

    //动态规划, 状态数组，初始状态，状态转换
    //类似剑指 Offer II 101. 分割等和子集
    public int findTargetSumWays2(int[] nums, int target) {
        int sum=Arrays.stream(nums).sum();
        if(target>sum)return 0;
        //target=willPositiveSum-willNegtiveSum. willPositiveSum+willNegtiveSum=sum. (sum-target)=2*willNegtiveSum
        if((sum-target)%2!=0)return 0;

        //willPositiveSum-willNegtiveSum=target
        int willNegtiveSum=(sum-target)/2;
        //动态规划数组。在[0:i]范围内取值=j
        int[][]dp=new int[nums.length+1][willNegtiveSum+1];

        //边界状态
        //在[0:0]范围内不取值=0的方案数为1
        dp[0][0]=1;
        //在[0:0]范围内取值=j的方案数为0，因为j>0
        for(int j=1;j<=willNegtiveSum;j++)dp[0][j]=0;

        //状态转换
        for(int i=1;i<=nums.length;i++){
            for(int j=0;j<=willNegtiveSum;j++){
                //在[0:i]范围内取值=j，j<nums[i], 不能取nums[i], 看dp[i-1][j]的结果
                if(j<nums[i-1])dp[i][j]=dp[i-1][j];
                //在[0:i-1]范围内取值=j-nums[i], 选取nums[i], 在[0:i]范围内取值=j
                else dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i-1]];
            }
        }

        return dp[nums.length][willNegtiveSum];
    }

    // 动态规划+滚动数组+倒序更新
    public int findTargetSumWays3(int[] nums, int target) {
        int sum=Arrays.stream(nums).sum();
        if(target>sum)return 0;
        if((sum-target)%2!=0)return 0;

        int willNegtiveSum=(sum-target)/2;
        //在[0:i]范围内取值=j。去掉i
        int[]dp=new int[willNegtiveSum+1];

        dp[0] = 1;
        for (int j = 1; j < willNegtiveSum + 1; j++) {
            dp[j] = 0;
        }

        for(int i=1;i<=nums.length;i++){
            //xcrj 倒序更新，保证靠小下标后更新
            //j>=nums[i]
            // for(int j=willNegtiveSum;j>=0;j--){
            //     //if(j<nums[i-1])dp[i][j]=dp[i-1][j];
            //     //else dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i-1]];
            //     if(j<nums[i-1])dp[j]=dp[j];
            //     else dp[j]=dp[j]+dp[j-nums[i-1]];
            // }
            //等价于上面的for循环
            for (int j = willNegtiveSum; j >= nums[i-1]; j--) {
                dp[j] = dp[j] + dp[j - nums[i-1]];
            }
        }

        return dp[willNegtiveSum];
    }
}
