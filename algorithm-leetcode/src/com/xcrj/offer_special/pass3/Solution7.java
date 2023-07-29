package com.xcrj.offer_special.pass3;

import java.util.*;

/**
 * 剑指 Offer II 007. 数组中和为0的三个数
 * - 数组元素是无序的
 * - 找出所有和为0的三元组
 * - 两个三元组中对应位置元素不能相同
 */
public class Solution7 {

    /**
     * 先升序排序
     * - 再使用"剑指 Offer II 006"的方法 双指针反向移动
     * - 利用set防止三元组重复
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    // set可以防止三元组重复
                    // xcrj ArrayList的equal()根据 对象引用相等||数组中对应位置元素值相等
                    set.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                }

                if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        // xcrj set创建list
        return new ArrayList<>(set);
    }

    /**
     * 不使用set防止三元组重复, 手动控制
     * 优化 遍历过程中相连的相等元素只使用最后1个，避免两个三元组对应位置元素相等
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 防止三元组重复 存在相等的i 之前已经处理过i 跳过 相等的i取第1个
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = 0 - nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 防止三元组重复 已经处理过1次的i，k，不再处理
                    while(j<k&&nums[j]==nums[++j]);
                    while(j<k&&nums[k]==nums[--k]);
                }
                
                if (nums[j] + nums[k] > target) {
                    k--;
                } 
                if (nums[j] + nums[k] < target) {
                    j++;
                }
            }
        }

        return list;
    }
}
