package com.xcrj.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum/
 * 两数之和
 * 同一个元素不能重复使用
 */
public class Main4 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if (map.containsKey(b)) {
                int idx = map.get(b);
                if (idx != i) {//
                    return new int[]{b, i};
                }
            }
        }
        return new int[]{};
    }
}
