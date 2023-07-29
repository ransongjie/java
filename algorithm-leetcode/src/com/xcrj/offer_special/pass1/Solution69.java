package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 069. 山峰数组的顶部
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 * - arr.length >= 3
 * - 存在 i（0 < i< arr.length - 1）使得：
 * -- arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * -- arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i，即山峰顶部。
 * <p>
 * 已知，arr[]是1个单峰数组
 * 山峰数组的顶部，左侧序列<arr[i]<右侧序列
 */
public class Solution69 {

    /**
     * 枚举遍历
     */
    public int peakIndexInMountainArray1(int[] arr) {
        // i=1~len-1, 山峰必须在数组中间
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找，往中间靠拢，往更高处靠拢
     * 二分查找，在山峰左右两侧的数组序列都是有序的
     * - arr[mid] > arr[mid + 1]，在山峰左侧序列中找更大值
     * - arr[mid] <= arr[mid + 1]，在山峰右侧序列中找更大值
     */
    public int peakIndexInMountainArray2(int[] arr) {
        // l=1,r=len-2, 山峰必须在数组中间
        int l = 1, r = arr.length - 2, o = -1;
        while (l <= r) {
            // int mid=(l+r)>>1;
            int mid = ((r - l) >> 1) + l;
            // 到左侧序列中找更大的值，1234 5 4321
            if (arr[mid] > arr[mid + 1]) {
                o = mid;
                r = mid - 1;
            }
            // 到右侧序列中找更大的值
            else {
                l = mid + 1;
            }
        }

        return o;
    }
}
