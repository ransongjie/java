package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 093. 最长斐波那契数列
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * - n >= 3
 * - 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 */
public class Solution93 {
    /**
     * 什么是斐波那契式子序列:
     * - arr[i]>arr[j]>arr[k]且arr[k]+arr[j]=arr[i]，则arr[k]、arr[j]和arr[i]三个元素组成一个斐波那契式子序列。
     * - 由于数组 arr 严格递增，因此arr[i]>arr[j]>arr[k] 等价于i>j>k。
     * - 斐波那契式子序列的长度至少为3
     * <p>
     * 如何确定整个斐波那契式子序列：
     * - 当下标i确定时，任何小于下标i的下标j都可能满足arr[j]是某个斐波那契式子序列中arr[i]前面的一个数字
     * - 只有当确定斐波那契式子序列的最后两个数字时，才能确定整个斐波那契式子序列。
     * <p>
     * 动态规划数组：
     * - dp[j][i]表示以arr[j]和arr[i]作为最后两个数字的斐波那契式子序列的最大长度。
     * - 初始时dp中的所有值都是0。
     * - arr[i]−arr[j]存在于数组中
     * -- 当dp[k][j]≥3时，dp[j][i]=dp[k][j]+1，i>j>k
     * -- 当dp[k][j]<3时，以arr[k]和arr[j]作为最后两个数字的斐波那契式子序列并不存在，但以arr[j]和arr[i]作为最后两个数字的斐波那契式子序列存在，且dp[j][i]=3。
     * - arr[i]−arr[j] 不存在于数组中，认为下标k<0
     * <p>
     * 状态转移方程
     * - 0≤k<j, dp[j][i]=max(dp[k][j]+1,3)
     * - k<0||k≥j, dp[j][i]=0
     * <p>
     * 保证状态转移方程
     * - 如何k<0：map.getOrDefault(arr[i]-arr[j],-1);
     * - 如何k<0时，dp[j][i]=0：k=-1时，不操作
     * - 如何k≥j：2 * arr[j] > arr[i]
     * - 如何k≥j，dp[j][i]=0时：for(2 * arr[j] > arr[i])
     * - 如何0≤k：if(k>=0)
     */
    public int lenLongestFibSubseq(int[] arr) {
        // 使用散列表加快搜索速度
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        // 初始时dp中的所有值都是0
        int[][] dp = new int[arr.length][arr.length];
        // 结果
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            /**
             * 倒序寻找以i结尾的可能的斐波那契式子序列
             * - 当arr[j]×2>arr[i]时，才满足arr[k]<arr[j]，即可能存在arr[k]，遍历更小的j
             * - 当arr[j]×2≤arr[i]时，不满足arr[k]<arr[j]，即不可能存在arr[k]，不遍历更小的j。等价于，k≥j，让dp[j][i]=0
             *
             * 斐波那契式子序列
             * - arr[i]>arr[j]>arr[k]且arr[k]+arr[j]=arr[i]
             */
            for (int j = i; j >= 0 && 2 * arr[j] > arr[i]; j--) {
                // -1，arr[i]−arr[j] 不存在于数组中，认为下标k<0
                // k<0时, dp[j][i]=0
                int k = map.getOrDefault(arr[i] - arr[j], -1);
                // 0≤k<j, dp[j][i]=max(dp[k][j]+1,3)
                // k<j被arr[j] > arr[i]保证
                if (k >= 0) {
                    // 0≤k<j, dp[j][i]=max(dp[k][j]+1,3)
                    dp[j][i] = Math.max(3, dp[k][j] + 1);
                }
                r = Math.max(r, dp[j][i]);
            }
        }
        return r;
    }
}
