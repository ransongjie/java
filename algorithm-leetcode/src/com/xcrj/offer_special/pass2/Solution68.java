package com.xcrj.offer_special.pass2;
/**
 * 剑指 Offer II 068. 查找插入位置
 * - 给定一个排序的整数数组 nums和一个整数目标值 target ，
 * - 在数组中找到了target，返回其位置
 * - 在数组中没找到target，返回其应该插入的位置
 * - 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class Solution68 {
    /**
     * xcrj 构建折半查找树(二叉排序树)
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int l=0,r=nums.length-1,o=nums.length;
        while(l<=r){
            // (r+l)>>1等于((r-l)>>1)+l
            int mid=(r+l)>>1;
            if(target<=nums[mid]){
                o=mid;
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return o;
    }
}
