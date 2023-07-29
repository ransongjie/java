package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 104. 排列的数目，和等于target的排列的数目
 * 输入，nums[] 由不同正整数组成，target 目标数
 * 输出，返回总和为target的元素组合个数
 * 
 * 组合个数：
 * 次数：数组中的数字可以在一次排列中出现任意次
 * 顺序：顺序不同的序列被视作不同的组合
 */
public class Solution104 {
    //动态规划
    public int combinationSum4(int[] nums, int target) {
        //dp[总和]=组合个数
        int[] dp=new int[target+1];

        //边界状态. dp[总和为0]=空组合，这1种组合
        dp[0]=1;

        //状态转换
        //xcrj 剩余总和组合数量=剩余总和组合数量+(剩余总和-遍历每个元素值)组合数量
        for(int i=0;i<=target;i++){
            for(int j=0;j<nums.length;j++){
                //dp[总和=i-nums[j]]=组合数量
                if(nums[j]<=i)dp[i]=dp[i]+dp[i-nums[j]];
            }
        }

        return dp[target];
    }
}
