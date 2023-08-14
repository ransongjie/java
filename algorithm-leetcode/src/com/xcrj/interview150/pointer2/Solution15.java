package com.xcrj.interview150.pointer2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum
 * 三数之和=0，三元组不可以重复
 * [-1,0,1] [1,0,-1] 重复
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return null;
        List<List<Integer>> res = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //以-nums[i]做target，在i+1~len-1中找sum=target的数
        int n = nums.length, l, r, target, sum;
        for (int i = 0; i < n; i++) {
            //去重，相邻相等只要第1个
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            target = 0 - nums[i];
            l = i + 1;
            r = n - 1;
            while (l < r) {
                sum = nums[l] + nums[r];
                if (target == sum) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //去重，相邻相等只要第1个
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    //继续寻找
                    l++;
                    r--;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }
}
