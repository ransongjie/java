package com.xcrj.interview150.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations
 * 全排列
 */
public class Solution03 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //转储
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        //回溯
        backtrack(list, nums.length, ans, 0);
        //返回结果
        return ans;
    }

    /**
     * 把0~n元素，尝试放到第0个位置
     * 把1~n元素，尝试放到第1个位置
     * ...
     *
     * @param list
     * @param n
     * @param ans
     * @param i
     */
    private void backtrack(List<Integer> list, int n, List<List<Integer>> ans, int i) {
        if (i == n) {
            ans.add(new ArrayList<>(list));
        } else {
            // 把i~n的数都尝试放到第i个位置
            for (int j = i; j < n; j++) {
                Collections.swap(list, i, j);
                backtrack(list, n, ans, i + 1);
                Collections.swap(list, i, j);
            }
        }
    }
}
