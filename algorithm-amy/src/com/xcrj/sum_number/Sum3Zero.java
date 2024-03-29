package com.xcrj.sum_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 双指针
 * 1个for+2个指针
 * https://leetcode.cn/problems/3sum/
 * 三数之和等于0，i!=l!=r
 */
public class Sum3Zero {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return null;
        List<List<Integer>> ans = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        int l, r, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            //nums[i]>0，三数之和一定大于0，不可能等于0
            if (nums[i] > 0) return ans;
            //去重，相邻相等只要1个
            while (i < nums.length && i > 0 && nums[i] == nums[i - 1]) i++;
            //双指针
            l = i + 1;
            r = nums.length - 1;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return ans;
    }
}
