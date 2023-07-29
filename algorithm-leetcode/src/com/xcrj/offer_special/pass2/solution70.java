package com.xcrj.offer_special.pass2;
/**
 * 剑指 Offer II 070. 排序数组中只出现一次的数字
 * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * 已知，
 * - 数组中元素数量为奇数
 * - 在出现1次元素的左边有偶数个元素右边有奇数个元素
 * - 数组是排好序的，因此出现两次的元素是相邻的
 */
public class solution70 {
    /**
     * 比如，1(0),1(1),2(2),3(3),3(4)找2
     * 只出现1次的数下标一定是偶数
     * 偶数和下一个数（奇数）比较，相等则继续往右找，不相等则继续往左找
     * 奇数和前一个数（偶数）比较，相等则继续往右找，不相等则继续往左找
     */
    public int singleNonDuplicate1(int[] nums) {
        int l = 0, r = nums.length - 1;
        // 
        while (l < r) {
            int mid=(r+l)>>1;
            /**
             * xcrj mid^1
             * - 当mid为奇数时，mid-1,mid-=mid&1
             * - 当mid为偶数时，mid-0,mid-=mid&1
             */
            if(nums[mid]==nums[mid^1]){
                l=mid+1;
            }else{
                r=mid;
            }
        }
        return nums[l];
    }

    /**
     * 只出现1次的数下标一定是偶数
     * @param nums
     * @return
     */
    public int singleNonDuplicate2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid=(r+l)>>1;
            // 确保mid是偶数
            mid-=mid&1;
            // nums[mid]等于nums[mid+1]，则出现1次的数一定在右边
            if(nums[mid]==nums[mid+1]){
                l=mid+2;
            }else{
                r=mid;
            }
        }
        return nums[l];
    }
}
