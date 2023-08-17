package com.xcrj.interview150.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum
 * 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 类似硬币币值问题
 * 组成目标值的硬币数量种数
 * 可重复选取同一个硬币(0~n次)
 * coins={硬币币值}
 * aim 目标值
 */
public class Solution04 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> an = new ArrayList<>();
        backtrack(candidates, target, 0, ans, an);
        return ans;
    }

    private void backtrack(int[] coins, int rest, int idx, List<List<Integer>> ans, List<Integer> an) {
        if (rest < 0) return;
        //遍历所有的硬币尝试组成rest
        if (rest == 0) {
            ans.add(new ArrayList<>(an));
            return;
        }
        if (idx == coins.length) return;

        //同一个硬币选取i次再去选取下一个
        for (int i = 0; i <= (rest / coins[idx]); i++) {//=
            for (int j = 0; j < i; j++) {
                an.add(coins[idx]);
            }
            backtrack(coins, rest - coins[idx] * i, idx + 1, ans, an);
            for (int j = 0; j < i; j++) {
                an.remove(an.size() - 1);
            }
        }
    }
}
