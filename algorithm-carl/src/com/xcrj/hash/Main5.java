package com.xcrj.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.cn/problems/4sum-ii/
 * 四数相加 2
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * 2
 * 两个元组如下:
 * (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class Main5 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n1 :
                nums1) {
            for (int n2 :
                    nums2) {
                int a = n1 + n2;
                map.put(a, map.getOrDefault(a, 0) + 1);
            }
        }

        int ans = 0;
        for (int n3 :
                nums3) {
            for (int n4 :
                    nums4) {
                int a = 0 - (n3 + n4);
                if (map.containsKey(a)) {
                    ans += map.get(a);
                }
            }
        }

        return ans;
    }
}
