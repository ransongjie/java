package com.xcrj.pointer2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/
 * 三数之和=0，i!=l!=r
 */
public class Main8 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return null;
        List<List<Integer>> res = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //以-nums[i]做target，在i+1~len-1中找sum=target的数
        int n = nums.length, l, r, sum;
        for (int i = 0; i < n; i++) {
            // 减枝，排序后 第1个元素>0，后续元素一定>0，sum=nums[i]+nums[l]+nums[r]>0 不可能=0
            if (nums[i] > 0) return res;
            //去重，相邻相等只要第1个
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 在i+1~len-1中找sum=target的数
            l = i + 1;
            r = n - 1;
            // 已经拍序
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (0 == sum) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //去重，相邻相等只要第1个
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    //继续寻找
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }
}
