package com.xcrj.permutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations
 * 全排列
 */
public class Main1 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lss = new ArrayList<>();
        //第1个排列
        List<Integer> ls = new ArrayList<>();
        for (int a :
                nums) {
            ls.add(a);
        }
        f(nums.length, 0, ls, lss);
        return lss;
    }

    /**
     * 回溯
     * 将i~n元素，尝试放到第i个位置
     * 把0~n元素，尝试放到第0个位置
     * 把1~n元素，尝试放到第1个位置
     * ...
     */
    private void f(int n, int idx, List<Integer> ls, List<List<Integer>> lss) {
        if (idx == n) {
            lss.add(new ArrayList<>(ls));
            return;
        }
        for (int i = idx; i < n; i++) {
            Collections.swap(ls, idx, i);
            f(n, idx + 1, ls, lss);
            Collections.swap(ls, idx, i);
        }
    }
}
