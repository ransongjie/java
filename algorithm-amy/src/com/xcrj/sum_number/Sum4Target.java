package com.xcrj.sum_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 双指针
 * 2个for+2个指针
 * https://leetcode.cn/problems/4sum/
 * 四数之和等于target
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * a、b、c 和 d 互不相同
 */
public class Sum4Target {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) return ans;
        //排序
        Arrays.sort(nums);
        int l, r, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            //剪枝, 4个数相加不可能等于target
            if (nums[i] > 0 && nums[i] > target) return ans;
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                //剪枝
                if (nums[j] > 0 && nums[j] > target - nums[i]) break;//break
                //去重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                l = j + 1;
                r = nums.length - 1;
                while (l < r) {
                    sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
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
        return ans;
    }
}
