package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指Offer II 061.和最小的k个数对
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 * 注意：
 * - nums1[] nums2[]都是升序排列的
 * - nums1[] nums2[]中的数可以重复使用
 */
public class Solution61 {
    /**
     * 已知
     * - nums1[]，nums2[]都是升序排列的
     * - (a1,b1)一定是最小的
     * - 对头元素最小
     * - 优先队列长度固定为k，只保留前k小的数对
     * <p>
     * 优先队列
     * - 先把(ai,b1)放到优先队列中
     * - 操作k次：出队，将对头（对头值最小）元素放入结果链表；入队，(出队的ai,出队的bi+1)
     */
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        // 定义优先队列
        Queue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 两个数对和之差(nums1[a],nums2[b]) (nums1[x],nums2[y])
                return (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]);
            }
        });

        // 先把(ai,b1)放到优先队列中
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            // 放入索引
            queue.offer(new int[]{i, 0});
        }

        // 操作k次：出队，将对头（对头值最小）元素放入结果链表；入队，(出队的ai,出队的bi+1)
        List<List<Integer>> rList = new ArrayList<>();
        while (k-- > 0 && !queue.isEmpty()) {
            // 出队，将对头（对头值最小）元素放入结果链表
            int[] cs = queue.poll();
            List<Integer> inList = new ArrayList<>(2);
            inList.add(nums1[cs[0]]);
            inList.add(nums2[cs[1]]);
            rList.add(inList);
            // 防止越界；入队，(出队的ai,出队的bi+1)
            if (cs[1] + 1 < nums2.length) queue.offer(new int[]{cs[0], cs[1] + 1});
        }

        return rList;
    }
}
