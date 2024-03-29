package com.xcrj.knapsack_01;
/**
 * https://leetcode.cn/problems/target-sum/submissions/
 * 494. 目标和
 * nums[]数组元素前添加+或-，求和等于target，方法数
 */
public class MainWay4 {
    /**
     * (前面添加+的元素和)-(前面添加-的元素和)=target
     * sum+target=2*(前面添加+的元素和)
     * 找到(sum+target)/2，就找到了结果
     * dp[n][(sum+target)/2]=方法数
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum=0;
        for (int num :
                nums) {
            sum+=num;
        }
        if(Math.abs(target)>sum) return 0;//元素前全部添加-或者+，都不能构成target
        if(((sum+target)&1)==1) return 0;//sum+target=2*(前面添加+的元素和)
        //问题转换
        int[]weight=nums;
        int[]value=nums;
        int bagSize=(sum+target)>>1;
        //dp数组
        int[][]dp=new int[nums.length][bagSize+1];
        //初始状态。注意顺序
        if(nums[0]<=bagSize){
            dp[0][nums[0]]=1;//放入背包的物品重量和，必须精确等于bagSize
        }
        int num0=0;//nums中0的个数
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0) num0++;
            dp[i][0]=(int)Math.pow(2,num0);//背包承重为0，方法数
        }
        //状态转移
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < bagSize+1; j++) {
                if(nums[i]>j){//第i件物品的重量>背包j, 第i件物品就放不进背包j
                    dp[i][j]=dp[i-1][j];
                }else{
                    //第i件物品不放入背包j，第i件物品放入背包j
                    dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i]];

                }
            }
        }

        //结果
        return dp[nums.length-1][bagSize];
    }

    public int findTargetSumWays2(int[] nums, int target) {
        int sum=0;
        for (int num :
                nums) {
            sum+=num;
        }
        if(Math.abs(target)>sum) return 0;//元素前全部添加-或者+，都不能构成target
        if(((sum+target)&1)==1) return 0;//sum+target=2*(前面添加+的元素和)
        //问题转换
        int[]weight=nums;
        int[]value=nums;
        int bagSize=(sum+target)>>1;

        //dp数组
        int[]dp=new int[bagSize+1];
        //初始状态
        dp[0]=1;//！背包承重为0，1个物品都不放入，也是一种方法
        //状态转换
        for (int i = 0; i < weight.length; i++) {//！从0开始
            for (int j = bagSize; j >=weight[i] ; j--) {
                dp[j]+=dp[j-weight[i]];
            }
        }
        //结果
        return dp[bagSize];
    }
}
