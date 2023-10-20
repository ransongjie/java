package com.xcrj.pass2.array.a_binary_search;

/**
 * 寻找插入的位置
 */
public interface Main01 {
    /**
     * 贪心
     * 寻找插入的位置
     * 
     * @param as
     * @param target
     * @return
     */
    static int searchInsert(int[] as, int target) {
        // as[]已经有序
        int i = 0;
        for (; i < as.length; i++) {
            // 插入到某个元素的前面
            if (as[i] >= target)
                return i;
        }
        // 或所有元素的后面
        return i;
    }

    static int searchInsert1(int[] as, int target) {
        int l = 0, r = as.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (as[mid] == target) {
                // 插入到相等元素之后
                return mid;
            } else if (as[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // 二分查找的null叶子结点，+1因为是闭区间
        return right + 1;
    }

    static int searchInsert2(int[] as, int target) {
        int l = 0, r = as.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (as[mid] == target) {
                // 插入到相等元素之后
                return mid;
            } else if (as[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 二分查找的null叶子结点
        return right;
    }
}
