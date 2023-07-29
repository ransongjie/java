package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指Offer II 061.和最小的k个数对
 * 升序排列的nums1 和 nums2，一个整数k 
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 
 * 注意：
 * - nums1[] nums2[]都是升序排列的
 * - nums1[] nums2[]中的数可以重复使用
 */
public class Solution61 {

    /**
     * 入队(ai,b0)>入队(ai,b1)>入队(ai,b2)
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // k is initialCapacity
        Queue<int[]> que=new PriorityQueue<>(k,(o1s,o2s)->(nums1[o1s[0]]+nums2[o1s[1]])-(nums1[o2s[0]]+nums2[o2s[1]]));

        // Math.min(nums1.length,k)
        for(int i=0;i<Math.min(nums1.length,k);i++){
            que.offer(new int[]{i,0});
        }

        // 
        List<List<Integer>> list=new ArrayList<>();
        while(k-->0&&!que.isEmpty()){
            int [] ts=que.poll();

            List<Integer> inList=new ArrayList<>();
            inList.add(nums1[ts[0]]);
            inList.add(nums2[ts[1]]);
            list.add(inList);

            if(ts[1]+1<nums2.length) que.offer(new int[]{ts[0],ts[1]+1});
        }

        return list;
    }
}
