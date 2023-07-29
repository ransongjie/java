package com.xcrj.offer_special.pass3;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 010. 和为k的连续子数组的数量
 * - 子数组的和等于k
 * - 子数组连续
 * - 求和为k的连续子数组的个数。
 */
public class Solution10 {
    /**
     * 边界指针倒序求解
     * - j做i的右边界
     * - i倒序从j到0，防止重复
     * - xcrj 防止重复 i每次都从1个新的起点j出发，如果i从0一定会重复
     * 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        int r=0;
        for(int j=0;j<nums.length;j++){
            int sum=0;
            for(int i=j;i>=0;i--){
                sum+=nums[i];
                if(sum==k) r++;
            }
        }

        return r;
    }

    /**
     * map<sum,次数>
     * 在存储当前和出现次数的过程中寻找前缀和
     * 前缀和+散列表
     * - 当前和-k=前缀和
     * - map<当前和,次数>
     * 
     * 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int r=0;
        // <当前和,次数>
        Map<Integer,Integer> map=new HashMap<>();
        // 初始化
        int sum=0;
        map.put(sum, 1);
        //
        for(int num:nums){
            sum+=num;
            if(map.containsKey(sum-k)){
                // 不同连续子数组的和=k的数量 等于 前缀和的数量
                r+=map.get(sum-k);
            }

            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return r;
    }
}
