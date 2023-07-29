package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 010. 和为k的子数组
 * - 子数组的和等于k
 * - 子数组连续
 * - 求和为k的连续子数组的个数。
 */
public class Solution10 {

    /**
     * 边界指针倒序求解
     * - i做j的右边界
     * - j倒序从i到0，防止重复
     * 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    r++;
                }
            }
        }
        return r;
    }

    /**
     * 前缀和+散列表
     * - 当前和-k=前缀和
     * - map<当前和,次数>
     * 初始当前和
     * for 数组：
     *  求当前和
     *  if map.containsKey(前缀和)
     *  当前和放入map<当前和,次数>
     * 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        // xcrj 初始条件
        int sum = 0;
        map.put(sum, 1);
        int r = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                r += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return r;
    }
}
