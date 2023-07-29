package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 006. 排序数组中两个数字之和
 * 给定一个已按照 升序排列 的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0开始计数 ，所以答案数组应当满足 0<= answer[0] < answer[1] <numbers.length。
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 */
public class Solution6 {
    /**
     * target-numbers[i]进行二分查找(折半查找)
     * 前提，有序序列，已知numbers[]数组序列有序
     */
    public int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int idx = binarySearch(numbers, target - numbers[i], i + 1, numbers.length - 1);
            // 二分查找查找到了结果
            if (-1 != idx) {
                return new int[]{i, idx};
            }
        }

        return new int[0];
    }

    /**
     * 二分查找（折半查找）
     * 目标值等于中间值返回索引
     * 目标值大于中间值往右边这一半查找
     * 目标值小于中间值往左边这一半查找
     */
    public int binarySearch(int[] numbers, int x, int start, int end) {
        int i = start;
        int j = end;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (x == numbers[mid]) return mid;
            if (x > numbers[mid]) i = mid + 1;
            else j = mid - 1;
        }

        return -1;
    }

    /**
     * 已知升序序列
     * 双指针 i j 反向移动，每次移动1个指针
     * ！！！双指针相向移动求和
     */
    public int[] twoSum2(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i, j};
            }
            // 小于，i右移
            if (sum < target) i++;
            // 大于，j左移
            else j--;
        }

        return new int[0];
    }

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        System.out.println(Arrays.toString(solution6.twoSum2(new int[]{1, 2, 4, 6, 10}, 8)));
    }
}
