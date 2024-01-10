package com.xcrj.greed;
/*
https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
K 次取反后最大化的数组和
第二步，三步总共2次贪心
第一步：将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
第二步：从前向后遍历，遇到负数将其变为正数，同时K--
第三步：如果K还大于0（为奇数），那么只反复转变数值最小的元素，将K用完
第四步：求和
 */
public class Solution7{
    public int largestSumAfterKNegations(int[] nums, int K) {
        // 按照绝对值从大到小排序
        nums=IntStream.of(nums).boxed()
        .sorted((o1,o2)->Math.abs(o1)<Math.abs(o2)?1:Math.abs(o1)==Math.abs(o2)?0:-1)
        .mapToInt(Integer::intValue)
        .toArray();

        // 从前向后遍历，遇到负数将其变为正数，同时K--
        for(int i=0;i<nums.length;i++){
            if(K>0&&nums[i]<0){
                nums[i]=-nums[i];
                K--;
            }
        }

        // K为奇数，将最小值变为相反数
        if((K&1)==1){
            nums[nums.length-1]=-nums[nums.length-1];
        }
        // 求和
        return Arrays.stream(nums).sum();
    }
}