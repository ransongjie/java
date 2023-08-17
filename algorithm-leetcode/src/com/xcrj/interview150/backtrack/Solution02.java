package com.xcrj.interview150.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.cn/problems/combinations
 * 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 */
public class Solution02 {
    /**
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k < 1 || n < k) return ans;
        Deque<Integer> dque = new ArrayDeque<>();
        backtrack(n, k, 1, dque, ans);
        return ans;
    }

    /**
     * 第1层n个选择，第2层n-1个选择,..., 选了k个数后返回
     *
     * @param n
     * @param k
     * @param i    从第i个数开始选择
     * @param dque
     * @param ans
     */
    private void backtrack(int n, int k, int i, Deque<Integer> dque, List<List<Integer>> ans) {
        if (dque.size() == k) {
            ans.add(new ArrayList<>(dque));
        } else {
            // 第1层n个选择，第2层n-1个选择
//            for (int j = i; j <= n; j++) {
            // 能选到k个数，才继续往下走
            for (int j = i; j <= n - (k - dque.size()) + 1; j++) {
                dque.offerLast(j);
                backtrack(n, k, j + 1, dque, ans);// !!! j+1
                dque.pollLast();
            }
        }
    }
}
