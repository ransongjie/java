package com.xcrj.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 不含有重复元素，允许重复选择相同元素，解集不能包含重复的组合
 * <p>
 * https://leetcode.cn/problems/combination-sum
 * 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 类似硬币币值问题
 * 组成目标值的硬币数量种数
 * 可重复选取同一个硬币(0~n次)
 * coins={硬币币值}
 * aim 目标值
 */
public class Main1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lss = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        f(candidates, target, 0, lss, ls);
        return lss;
    }

    //回溯
    private void f(int[] coins, int rest, int idx, List<List<Integer>> lss, List<Integer> ls) {
        if (rest < 0) return;
        if (rest == 0) {
            lss.add(new ArrayList<>(ls));
            return;
        }
        if (idx == coins.length) return;

        //同1个硬币可以最多选取i次
        for (int i = 0; i <= (rest / coins[idx]); i++) {
            //选取同1个硬币i次
            for (int j = 0; j < i; j++) {
                ls.add(coins[idx]);
            }
            //递进
            f(coins, rest - coins[idx] * i, idx + 1, lss, ls);
            //回溯
            for (int j = 0; j < i; j++) {
                ls.remove(ls.size() - 1);
            }
        }
    }
}
