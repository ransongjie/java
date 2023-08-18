package com.xcrj.interview150.binary_search;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array
 * [4,5,6,7,0,1,2]
 * 锯齿形状
 * 两两互不相同
 */
public class Solution03 {

    /**
     * 0~n，只在某个位置出现了断层，断层两侧都是递增的
     * 0~断层 递增, 断层~n 递增, 0~断层 元素值>断层~n 元素值
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (target == nums[mid]) return mid;
            //l~mid l在mid左侧 l<=mid 断层一定不会在l~mid中间
            if (nums[l] <= nums[mid]) {
                // l,target,mid,r
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                }
                // l,mid,target,r
                else {
                    l = mid + 1;
                }
            }
            //l~mid l在mid左侧 l<=mid 断层一定在l~mid中间
            else {
                // l,mid,target,r。 mid~r中间一定没有断层
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                }
                // l,target,mid,r
                else {
                    r = mid - 1;
                }
            }
        }

        return -1;
    }

    public int search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            /**
             * 3项包括了所有情况
             * 3项不可能全真/全假
             * 只有1项为真 结果为真
             * 只有2项为真 结果为假
             *
             * 所有情况
             * - target在第1个分段中, 一定有 A=false
             * - target在第2个分段中, 一定有 A=true
             */
            if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (nums[mid] < target)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l == r && nums[l] == target ? l : -1;
    }
}
