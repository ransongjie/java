package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 007. 数组中和为 0 的三个数
 * 数组是无序的
 * 给定一个包含 n 个整数的数组nums，
 * 判断nums中是否存在三个元素a ，b ，c ，使得a + b + c = 0 ？
 * 请找出所有和为 0 且不重复的三元组
 * - 数组是无序的
 * - 所有 a + b + c = 0&&a b c都不相同
 */
public class Solution7 {

    /**
     * 转换为剑指 Offer II 006. 排序数组中两个数字之和 问题
     * 对数组排序
     * a + b = c
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        // 结果，
        Set<List<Integer>> set = new HashSet<>(3);
        // 升序排序 上面set？，题目要求结果不重复，例如 -4,-1,-1,0,1，2。[-1,0,1],[-1,0,1]就是重复的
        Arrays.sort(nums);
        // 双指针
        for (int k = 0; k < nums.length; k++) {
            // target=-c
            int target = 0 - nums[k];
            // 从k的下一个元素开始应用双指针
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    // asList(T... a) 传入可变参数，变长参数
                    // !! add值相同则丢弃 (e==null ? e2==null : e.equals(e2))
                    // !! ArrayList的equal() 根据 对象引用相等||数组中元素值相等
                    set.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    // 存在新的a+b+c=0的情况
                    i++;
                    j--;
                } else if (sum < target) i++;
                else j--;
            }
        }

        // ArrayList(Collection<? extends E> c)
        return new ArrayList<>(set);
    }

    /**
     * 转换为剑指 Offer II 006. 排序数组中两个数字之和 问题
     * 对数组排序
     * a + b = c
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        // 结果，
        List<List<Integer>> lists = new ArrayList<>(3);
        // 升序排序 上面set？，题目要求结果不重复，例如 -4,-1,-1,0,1，2。[-1,0,1],[-1,0,1]就是重复的
        Arrays.sort(nums);
        // 双指针
        for (int k = 0; k < nums.length; k++) {
            // 去重k，k+1和k个元素值相同，处理1个即可，处理第k个即可
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            // target=-c
            int target = 0 - nums[k];
            // 从k的下一个元素开始应用双指针
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    // asList(T... a) 传入可变参数，变长参数
                    lists.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    // 去重，要求a+b+c=0, a b c都不相同
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
                else if (sum < target) i++;
                else j--;
            }
        }

        return lists;
    }

    public static void main(String[] args) {
        Solution7 solution7 = new Solution7();
        System.out.println(solution7.threeSum2(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
