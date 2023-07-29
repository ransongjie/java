package com.xcrj.offer_special.pass1;

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
public class Solution70 {
    /**
     * 全数组二分查找元素个数奇偶分界线
     * <p>
     * 已知
     * - 数组中元素数量为奇数
     * - 在出现1次元素的左边有偶数个元素右边有奇数个元素
     * - 数组是排好序的，因此出现两次的元素是相邻的
     */
    public int singleNonDuplicate1(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            /**
             * mid为偶数时，查看nums[mid]==nums[mid+1]
             * mid为奇数时，查看nums[mid]==nums[mid-1]
             * 上面统一为 nums[mid]==nums[mid^1]。当mid为偶数时，mid^1等价于mid+1，因为mid最后1位为0；当mid为奇数时，mid^1等价于mid-1，因为mid最后1位为1
             */
            // 相等则证明在元素奇偶数量分界线左边，往右侧靠拢
            if (nums[mid] == nums[mid ^ 1]) {
                l = mid + 1;
            }
            // 不相等则证明在元素奇偶数据分界线右边，往左侧靠拢
            else {
                r = mid;
            }
        }
        // l=r时，退出while循环，
        return nums[l];
    }

    /**
     * 在数组偶数部分二分查找满足nums[x]!=nums[x+1]的最小偶数下标x，在查找过程中保证x是偶数
     * <p>
     * 已知
     * - 只出现一次元素的下标一定是偶数
     * - 数组元素个数是奇数
     */
    public int singleNonDuplicate2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            /**
             * 确保mid是偶数
             * - 当mid为奇数时，mid-1,mid-=mid&1
             * - 当mid为偶数时，mid-0,mid-=mid&1
             */
            mid -= mid & 1;
            // nums[偶数mid]==nums[偶数mid+1]，往右侧靠拢
            if (nums[mid] == nums[mid + 1]) {
                // +2.跳过mid+1指向的元素，
                l = mid + 2;
            }
            // 往左靠拢
            else {
                r = mid;
            }
        }
        return nums[l];
    }
}
