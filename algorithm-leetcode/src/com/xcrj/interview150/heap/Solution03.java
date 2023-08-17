package com.xcrj.interview150.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/find-k-pairs-with-smallest-sums
 * 查找和最小的 K 对数字
 * <p>
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 */
public class Solution03 {
    /**
     * 和小根堆
     * 把小的入堆，直到达标k对
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //int[0]来自nums1, int[1]来自nums2。按数对之和正序排序
        PriorityQueue<int[]> pque = new PriorityQueue<>((a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        List<List<Integer>> ans = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        /**
         * 第1次添加完所有a，1个b
         * - nums1: 0 1 2 3 4
         * - nums2: 0
         * 第i次添加小的a，小的b+1 (变化1个)
         * - nums1: a
         * - nums2: b+1
         */
        for (int i = 0; i < Math.min(len1, k); i++) {
            pque.offer(new int[]{i, 0});
        }
        /**
         *
         */
        while (k-- > 0 && !pque.isEmpty()) {
            //获取和最小的数对
            int[] idxPair = pque.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            //idxPair[0]和idxPair[1]较小，nums1所有元素都添加完了
            //只变化1个idxPair[1] + 1和会小一些
            if (idxPair[1] + 1 < len2) {
                pque.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    boolean flag = true;

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        // n>m 则换个执行方向，确保nums1是长度更小的数组，n<=m
        if (n > m) {
            flag = false;
            return kSmallestPairs(nums2, nums1, k);
        }
        //
        List<List<Integer>> ans = new ArrayList<>();
        //int[0]来自nums1, int[1]来自nums2。按数对之和正序排序
        PriorityQueue<int[]> pque = new PriorityQueue<>((a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        /**
         * 第1次添加完所有a，1个b
         * - 长度短的nums: 0 1 2 3 4
         * - 长度长的nums: 0
         * 第2次添加小的a，小的b+1
         * - 长度短的nums: 0
         * - 长度长的nums: 1
         */
        for (int i = 0; i < Math.min(n, k); i++) pque.offer(new int[]{i, 0});
        int[] idxs;
        while (ans.size() < k && !pque.isEmpty()) {
            // pque中排好序，和最小就在对头，这里只是添加
            idxs = pque.poll();
            int a = idxs[0];//i
            int b = idxs[1];//0
            //ans.add((nums1[a],nums2[b])) flag=true nums1更短
            ans.add(new ArrayList() {{
                add(flag ? nums1[a] : nums2[b]);
                add(flag ? nums2[b] : nums1[a]);
            }});
            /**
             * nums都递增有序
             * 之前添加所有a和1个b
             * 0 1 2 3 4
             * 0
             * 这里再添加可能较大的
             * nums[a]值小才会添加进去，再添加一个稍大的b
             */
            if (b + 1 < m) pque.offer(new int[]{a, b + 1});
        }
        return ans;
    }
}
