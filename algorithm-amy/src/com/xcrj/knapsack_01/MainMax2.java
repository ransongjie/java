package com.xcrj.knapsack_01;
/**
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 * 分割等和子集
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11]
 */
public class MainMax2 {
    /**
     * 从nums数组中挑物品，放入最大承重为sum/2背包，背包最大价值=sum/2，可以nums能被等和分割
     * weight=nums
     * value=nums
     * bagSize=sum/2
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int num :
                nums) {
            sum+=num;
        }
        if((sum&1)==1){
            return false;//奇数不能被分割为等和子集
        }
        //问题转换
        int[]weight=nums;
        int[]value=nums;
        int bagSize=sum>>1;

        //dp数组
        int[]dp=new int[bagSize+1];
        //初始状态
        for (int i = weight[0]; i < bagSize; i++) {
            dp[i]=value[0];//仅有第0件物品
        }
        //状态转换
        for (int i = 1; i < nums.length; i++) {//先物品再背包
            for (int j = bagSize; j >=weight[i] ; j--) {
                dp[j]=Math.max(dp[j],value[i]+dp[j-weight[i]]);
            }
            if(dp[bagSize]==sum/2) return true;//dp[i][bagSize]=sum/2, 在i件物品中选择的物品之和=sum/2
        }
        //结果
        return false;
    }
}
