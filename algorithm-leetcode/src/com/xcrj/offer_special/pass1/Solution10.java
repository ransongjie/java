package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 010. 和为 k 的子数组
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 */
public class Solution10 {
    /**
     * 边界指针，i做j的边界指针
     * 0~j~i, j在0到i之间寻找满足条件的连续子数组
     * i为j的右边界
     * 0为j的左边界
     */
    public int subarraySum1(int[] nums, int k) {
        int number = 0;
        // i为j的右边界
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            // 0为j的左边界
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) number++;
            }
        }
        return number;
    }

    /**
     * 前缀和+散列表
     * sumPre[j]+k=sumPre[i]
     * sum(0~j)+sum(j~j+k)=sum(0~i)
     * 有多少个sumPre[j]，就有多少个k。sumPre[j]由sumPre[i]而来
     */
    public int subarraySum2(int[] nums, int k) {
        // 存储<前缀和,次数>
        Map<Integer, Integer> map = new HashMap<>(3);
        // 前缀为0，0个元素时，只出现1次
        map.put(0, 1);

        int pre = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.subarraySum2(new int[]{1, 1, 1}, 2));
    }
}
