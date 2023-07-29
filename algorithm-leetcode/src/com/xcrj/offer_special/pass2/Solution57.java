package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 剑指 Offer II 057. 值和下标之差都在给定的范围内
 * 给你一个整数数组 nums 和两个整数k 和 t 
 * abs(nums[i] - nums[j]) <= t 
 * abs(i - j) <= k 
 */
public class Solution57 {
    /**
     * TreeSet（有序集合）+滑动窗口
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        TreeSet<Long> treeSet=new TreeSet<>();
        for(int i=0;i<nums.length;i++){
            Long numj=treeSet.ceiling((long)nums[i]-t);
            if(numj!=null&&(numj-t)<=nums[i]) return true;
            treeSet.add((long)nums[i]);
            // 滑动窗口大小为k
            if(i>=k) treeSet.remove((long)nums[i-k]);
        }
        return false;
    }

    /**
     * 滑动窗口+桶排序思想
     * - 滑动窗口，限制索引差值 i-j<=k
     * - 桶容量，限制值差值 x-y<=t
     * - 桶编号，把x放到id=x/w的桶里面
     * <p>
     * 桶排序：类比学生按成绩等级排序
     * - 桶数量：学生成绩等级数量
     * - 桶编号：学生成绩等级，90到100分成绩是A等级存储到A等级桶，80到90分成绩是B等级存储到B等级桶，依次类推
     * - 桶容量：10分值
     * <p>
     * 桶排序思想：
     * - 桶数量：map.size()
     * - 桶编号：x/w
     * - 桶容量：t+1，x/w（w是桶的容量，整体是桶的编号）
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        Map<Long,Long> bucekt=new HashMap<>();
        long v=t+1;
        for(int i=0;i<nums.length;i++){
            long bucektID=getBucketID(nums[i], v);

            if(bucekt.containsKey(bucektID)) return true;
            if(bucekt.containsKey(bucektID-1)&&Math.abs(bucekt.get(bucektID-1)-nums[i])<=t) return true;
            if(bucekt.containsKey(bucektID+1)&&Math.abs(bucekt.get(bucektID+1)-nums[i])<=t) return true;

            bucekt.put(bucektID, (long)nums[i]);
            if(i>=k)bucekt.remove(getBucketID(nums[i-k], v));
        }

        return false;
    }

    /**
     * 获取桶编号
     * @param x
     * @param v
     * @return
     */
    public long getBucketID(long x, long v) {
        if(x>=0) return x/v;
        // xcrj 不是 (x+1)/(v-1)
        else return (x+1)/v-1;
    }
}
