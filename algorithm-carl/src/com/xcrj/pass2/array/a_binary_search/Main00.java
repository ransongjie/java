package com.xcrj.pass2.array.a_binary_search;

/**
 * 二分查找的两种书写方式，闭区间方式和右开区间方式
 */
public class Main00 {
    public static void main(String[] args) {
        int[] as = { 1, 2, 3, 4, 7, 9, 10 };
        System.out.println(binarySearch1(as, 2));
        System.out.println(binarySearch2(as, 2));
    }

    /**
     * [a,b]闭区间
     * 
     * @param as
     * @param target
     * @return 下标 或 -1
     */
    static int binarySearch1(int[] as, int target) {
        int l = 0, r = as.length - 1;// 闭区间
        while (l <= r) {// 闭区间
            int mid = (l + r) >> 1;
            if (as[mid] == target) {
                return mid;
            } else if (as[mid] > target) {
                r = mid - 1;// 闭区间
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * [a,b)右开区间
     * 
     * @param as
     * @param target
     * @return 下标 或 -1
     */
    static int binarySearch2(int[] as, int target) {
        int l = 0, r = as.length;// 右开区间
        while (l < r) {// 右开区间
            int mid = (l + r) >> 1;
            if (as[mid] == target) {
                return mid;
            } else if (as[mid] > target) {
                r = mid;// 右开区间
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

}
