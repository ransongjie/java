package com.xcrj.offer_special.pass4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组中和为0的三个数
 * List<List<Integer>>
 */
public class Solution007 {
    /**
     * 排序
     * 两数之和=target
     * 首尾双指针
     * 去重
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rs = new ArrayList<>();
        int i = 0, n = nums.length, target, k, j, sum;
        Arrays.sort(nums);
        for (; i < n; i++) {
            //去重，只处理一个
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            target = 0 - nums[i];
            k = i + 1;
            j = nums.length - 1;
            while (k < j) {
                sum = nums[k] + nums[j];
                if (sum == target) {
                    rs.add(Arrays.asList(nums[i], nums[k], nums[j]));
                    //去重
                    while (k < j && nums[k] == nums[++k]) ;
                    while (k < j && nums[j] == nums[--j]) ;
                } else if (sum > target) {
                    j--;
                } else {
                    k++;
                }
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        Solution007 solution007 = new Solution007();
        int[] nums = {1, -1, -1, 0};
        List<List<Integer>> rs = solution007.threeSum(nums);
        System.out.println();
    }
}
