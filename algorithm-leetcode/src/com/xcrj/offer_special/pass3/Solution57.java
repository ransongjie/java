package com.xcrj.offer_special.pass3;

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
     * 下标在给定的范围内：滑动窗口
     * 值在给定的范围内：存在 (nums[i]-t)<=nums[j], 获取大于等于(nums[i]-t)的值即可
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet=new TreeSet<>();
        for(int i=0;i<nums.length;i++){
            /**
             * abs(nums[i] - nums[j]) <= t
             * ceiling, nums[i] - nums[j] <=t 
             * 还需要再判断, numj-nums[i]<=t
             */
            Long numj=treeSet.ceiling((long)nums[i]-t);
            if(numj!=null&&(long)numj-nums[i]<=t) return true;
            treeSet.add((long)nums[i]);
            if(i>=k) treeSet.remove((long)nums[i-k]);
        }

        return false;
    }

    /**
     * 桶排序+滑动窗口
     * 每个桶的容量为t+1
     * - Map<桶编号, nums[i]>
     * - nums[i], nums[j]在同一个桶里, nums[i]-nums[j]一定<=t
     * - nums[i], nums[j]在下一个桶里, 需要比较nums[i]-nums[j]<=t 
     * - nums[i], nums[j]在上一个桶里, 需要比较nums[i]-nums[j]<=t 
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        Map<Long,Long> bucket=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            long bucketID=getBucketID(nums[i], t+1);
            if(bucket.containsKey(bucketID))return true;
            if(bucket.containsKey(bucketID-1)&&Math.abs(bucket.get(bucketID-1)-nums[i])<=t)return true;
            if(bucket.containsKey(bucketID+1)&&Math.abs(bucket.get(bucketID+1)-nums[i])<=t)return true;
            //
            bucket.put(bucketID, (long)nums[i]);
            //
            if(i>=k) bucket.remove(getBucketID(nums[i-k], t+1));
        }

        return false;
    }

    /**
     * 
     * @param x nums[i]
     * @param v t+1 容量
     * @return
     */
    private long getBucketID(int x,int v){
        if(x>=0) return x/v;
        else{
            // x=-1放到-1这个桶, 而不是0这个桶
            return ((x+1)/v)-1;
        }
    }
}
