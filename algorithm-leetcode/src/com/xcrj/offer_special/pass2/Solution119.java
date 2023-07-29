package com.xcrj.offer_special.pass2;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 119. 最长连续序列，xcrj 不要求在原序列中从左到右递增
 * 输入，nums[]
 * 输出，最长连续序列的长度
 * 注意，c，a，b都在nums中，b比a大1即可，c比b大1即可
 */
public class Solution119 {
    //hash表
    public int longestConsecutive(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums)set.add(num);

        int maxr=0;
        for(int num:nums){
            //xcrj 先找最小的数, 没有比我更小的数，则我是最小的数
            if(!set.contains(num-1)){
                int currNum=num;
                int r=0;
                //set中是否包含大1的数
                while(set.contains(currNum)){
                    currNum++;
                    r++;
                }
                maxr=Math.max(maxr, r);
            }
        }

        return maxr;
    }
}
