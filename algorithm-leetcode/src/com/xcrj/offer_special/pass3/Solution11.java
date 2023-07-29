package com.xcrj.offer_special.pass3;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 011. 0和1个数相同的连续最长子数组
 * - 含有相同数量的0和1的子数组
 * - 子数组连续
 * - 最长子数组
 */
public class Solution11 {
    /**
     * 问题转化，将0看成-1,0和1个数相同的子数组，-1和1个数相同的子数组，sum=0
     * 前缀和：map<当前和,索引>, 前缀和索引,中间和=0,当前和索引
     * 
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        int maxLen=0;
        Map<Integer,Integer> map=new HashMap<>();
        // 初始化
        int sum=0;
        map.put(sum, -1);
        for(int i=0;i<nums.length;i++){
            // 求和
            if(nums[i]==0){
                sum--;
            }else {
                sum++;
            }

            // 前缀和判断
            if(map.containsKey(sum-0)){
                int preSumIdx=map.get(sum-0);
                maxLen=Math.max(maxLen, i-preSumIdx);
            }else{
                // xcrj else, 求最大长度，当前和-0=前缀和，当前和=前缀和，当前和索引更大
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}
