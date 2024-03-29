package com.xcrj.sum_number;

import java.util.HashMap;
import java.util.Map;

/**
 * hash表
 * https://leetcode.cn/problems/two-sum/
 * 两数之和 num1+num2=target
 * 同一个元素不能重复使用
 */
public class Sum2Target {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();//map<数字,索引>
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if (map.containsKey(b) && map.get(b) != i) {
                return new int[]{map.get(b), i};
            }
        }
        return new int[]{};
    }
}
