package com.xcrj.combination;

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
public class Main4a {
    /**
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k < 1 || k > n) return ans;
        Deque<Integer> dque = new ArrayDeque<>();
        f(1, n, k, dque, ans);//1
        return ans;
    }

    /**
     * n=6 k=2
     * 1~5 2~5 3~5 4~5 5~5
     * 2~6 3~6 4~6 5~6 6~6
     * 纵轴-递归：idx和k
     * -12，13，14，15，16
     * -22，23，24，25，26
     * 横轴-for：idx和n
     *
     * @param idx
     * @param n
     * @param k
     * @param dque
     * @param ans
     */
    private void f(int idx, int n, int k, Deque<Integer> dque, List<List<Integer>> ans) {
        if (k == dque.size()) {
            ans.add(new ArrayList<>(dque));
            return;
        }
        //n-(restK)+1
        for (int i = idx; i <= n - (k - dque.size()) + 1; i++) {
            dque.offerLast(i);
            f(i + 1, n, k, dque, ans);//i+1
            dque.pollLast();
        }
    }
}
