package com.xcrj.binary_search;
/**
 * 二分查找
 * https://leetcode.cn/problems/binary-search/description/
 *
 * 两种写法有3处不同
 */
public class Main0 {
    public static void main(String[] args) {
        int[] as = {1, 3, 4, 5, 6, 9};
        System.out.println(binarySearch1(as,3));
        System.out.println(binarySearch2(as,3));
    }

    //闭区间
    public static int binarySearch1(int[] as, int target) {
        int left = 0, right = as.length - 1;//
        while (left <= right)//
        {
            int middle = (left + right) >> 1;
//            int middle = left + (right - left) / 2;//防止越界
            if (as[middle] == target) {
                return middle;
            } else if (as[middle] > target) {
                right = middle - 1;//
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    //开区间
    public static int binarySearch2(int[] as, int target) {
        int left = 0, right = as.length;//
        while (left < right)//
        {
            int middle = (left + right) >> 1;
            if (as[middle] == target) {
                return middle;
            } else if (as[middle] > target) {
                right = middle;//
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
