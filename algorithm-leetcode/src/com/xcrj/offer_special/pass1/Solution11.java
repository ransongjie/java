package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 0和1个数相同的子数组
 * 给定一个二进制数组 nums , 找到含有相同数量的0和1的最长连续子数组，并返回该子数组的长度。
 */
public class Solution11 {
    /**
     * 前缀和+散列表
     * nums[]中元素0认为是-1，元素1认为是1
     * <preSum,对应下标>，preSum(0~下标)
     * preSum[j]+k=preSum[i]，若preSum[i]等于preSum[j]则k=0，所以“下标j到下标i”元素之和为0。元素个数为下标i-下标j
     */
    public int findMaxLength(int[] nums) {
        // <preSum,对应下标>，preSum(0~下标)
        Map<Integer, Integer> map = new HashMap<>(3);
        // 元素0认为是-1,元素1认为是1。前缀和
        int preSum = 0;
        // <没有前缀,对应下标-1>
        map.put(preSum, -1);
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (0 == nums[i]) preSum--;
            else preSum++;
            // preSum[j]+k=preSum[i]，若preSum[i]等于preSum[j]则k=0，所以“下标j到下标i”元素之和为0。元素个数为下标i-下标j
            if (map.containsKey(preSum)) {
                maxLen = Math.max(maxLen, i - map.get(preSum));
            } else {
                // <preSum,对应下标>，preSum(0~下标)
                map.put(preSum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        System.out.println(solution11.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
    }
}
