package com.xcrj.offer_special.pass4;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为 k 的子数组
 * 和为 k 的子数组的数量
 */
public class Solution010 {
    /**
     * pre+k=cur
     * 散列表记录前缀和出现次数
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int i = 0, n = nums.length, preSum = 0, ans = 0;
        Map<Integer, Integer> sumNum = new HashMap<>();
        sumNum.put(preSum, 1);
        for (; i < n; i++) {
            preSum += nums[i];
            ans += sumNum.getOrDefault(preSum - k, 0);
            sumNum.put(preSum, sumNum.getOrDefault(preSum, 0) + 1);
        }
        return ans;
    }
}
