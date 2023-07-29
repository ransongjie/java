package com.xcrj.offer_special.pass3;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 004. 只出现一次的数字
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 */
public class Solution4 {
    /**
     * 散列表统计每个数字出现的次数
     */
    public int singleNumber1(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            // getOrDefault()
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        // filter寻找满足条件的项目，寻找第一项即可
        return map.entrySet().stream().filter(entry->entry.getValue()==1).findFirst().get().getKey();
    }

     /**
     * 数组中只有出现1次或3次的数字，每个数字的二进制表示，每1位取值为0或1
     * 所有数字二进制表示的 第i位之和%3=出现1次数字二进制表示 第i位值
     */
    public int singleNumber2(int[] nums) {
        int r=0;
        //求出现1次数字的所有32位bit值，求出现1次数字第i位bit值，求所有数字第i位之和%3，求单个元素第i位的值
        for(int i=0;i<32;i++){
            int sum=0;
            for(int num:nums){
                sum+=(num>>i)&1;
            }

            // 出现1次数字，第i位为1
            // xcrj 将结果值第i位置为1, 1左移i位后 与原结果 按位或
            if(sum%3==1){
                r|=(1<<i);
            }
        }

        return r;
    }
}
