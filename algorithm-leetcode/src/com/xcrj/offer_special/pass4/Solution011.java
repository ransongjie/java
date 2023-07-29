package com.xcrj.offer_special.pass4;

import java.util.HashMap;
import java.util.Map;

/**
 * 0 和 1 个数相同的子数组
 * 0 和 1 个数相同的连续子数组的最大长度
 */
public class Solution011 {
    /**
     * 0做-1,1做1，个数相同，和为0
     * 散列表记录前缀和下标
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        int i = 0, n = nums.length, ans = 0, preSum = 0;
        Map<Integer, Integer> sumIdx = new HashMap<>();
        sumIdx.put(preSum, -1);
        for (; i < n; i++) {
            if (nums[i] == 0) {
                preSum--;
            } else {
                preSum++;
            }
            //pre+0=sum
            if (sumIdx.containsKey(preSum)) {
                ans = Math.max(ans, i - sumIdx.get(preSum));
            } else {//求最大长度，优先保留之前的下标
                sumIdx.put(preSum, i);
            }
        }
        return ans;
    }
}
