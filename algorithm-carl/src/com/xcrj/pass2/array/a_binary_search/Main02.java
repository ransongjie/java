package com.xcrj.pass2.array.a_binary_search;

/**
 * 有序数组中等于target的子数组的左右边界
 */
public class Main02 {
    /**
     * 
     * @param nums
     * @param target
     * @return target leftBorder rightBorder
     */
    static int[] searchRange(int[] nums, int target) {
        int leftBorder = leftBorder(nums, target);
        int rightBorder = rightBorder(nums, target);
        if (leftBorder == -2 || rightBorder == -2)
            return new int[] { -1, -1 };
        // 至少有1个元素。左右边界都往中间走1步。+1 因为leftBorder=middle-1，-1 因为rightBorder=middle+1
        if (rightBorder - leftBorder > 1)
            return new int[] { leftBorder + 1, rightBorder - 1 };
        return new int[] { -1, -1 };
    }

    /**
     * 
     * @param as
     * @return 左边界 或 -2
     */
    static int leftBorder(int[] as, int target) {
        int l = 0, r = as.length - 1, leftBorder = -2;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (as[mid] >= target) {// >=
                r = mid - 1;
                leftBorder = r;// 往左边移动确定左边界
            } else {
                l = mid + 1;
            }
        }
        return leftBorder;
    }

    /**
     * 
     * @param as
     * @return 左边界 或 -2
     */
    static int rightBorder(int[] as, int target) {
        int l = 0, r = as.length - 1, rightBorder = -2;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (as[mid] <= target) {
                l = mid + 1;
                rightBorder = r;// 往右边移动确定右边界
            } else {
                r = mid - 1;
            }
        }
        return rightBorder;
    }

}
