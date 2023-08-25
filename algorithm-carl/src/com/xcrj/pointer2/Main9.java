package com.xcrj.pointer2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/4sum/
 * 四数之和
 */
public class Main9 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;
        //排序
        Arrays.sort(nums);
        //以-nums[i]做target，在i+1~len-1中找sum=target的数
        int n = nums.length, l, r;
        long sum;
        for (int k = 0; k < n; k++) {
            // 减枝
            if (nums[k] > 0 && nums[k] > target) return res;
            // 去重
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            for (int i = k + 1; i < n; i++) {
                //去重，相邻相等只要第1个
                if (i > k + 1 && nums[i] == nums[i - 1]) continue;
                // 在i+1~len-1中找sum=target的数
                l = i + 1;
                r = n - 1;
                // 已经拍序
                while (l < r) {
                    sum = (long) nums[k] + nums[i] + nums[l] + nums[r];
                    if (target == sum) {
                        res.add(Arrays.asList(nums[k], nums[i], nums[l], nums[r]));
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
        }
        return res;
    }
}
