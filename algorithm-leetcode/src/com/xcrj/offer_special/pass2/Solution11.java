package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 011. 0 和 1 个数相同的子数组
 * - 含有相同数量的0和1的子数组
 * - 子数组连续
 * - 最长子数组
 */
public class Solution11 {
    /***
     * 问题转化
     * - 遇到0看成-1
     * - 遇到1看成1
     * - 和为0
     * 前缀和+散列表
     * - 当前和-0=前缀和
     * - map<当前和,下标>
     * 初始当前和
     * for 数组：
     * 求当前和
     * if map.containsKey(前缀和)
     * // 求最长子数组
     * else 当前和放入map<当前和,下标>
     * 
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        // 找不到含有相同数量的0和1的连续子数组，长度为0
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap();
        // 初始当前和
        int sum = 0;
        map.put(sum, -1);
        for (int i = 0; i < nums.length; i++) {
            // xcrj 问题转化
            if (0 == nums[i]) {
                sum--;
            } else {
                sum++;
            }

            if (map.containsKey(sum - 0)) {
                maxLen = Math.max(maxLen, i - map.get(sum - 0));
            }
            // xcrj 为什么需要else，因为求最长子数组。
            else{
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}
