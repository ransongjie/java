package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 081. 允许重复选择元素的组合
 * - 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * - candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
 * - 对于给定的输入，保证和为target的唯一组合数少于150个。
 */
public class Solution81 {
    List<List<Integer>> rss = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 回溯法
     * - 确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     * 剪枝函数
     * - 约束函数：剪去不满足约束条件的子树
     * - 限界函数：剪去得不到需要解（无解，非最优解）的子树
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0);
        return rss;
    }

    private void dfs(int[] candidates, int target, int i) {
        // 退出
        if (i == candidates.length) return;
        // 达到目标
        if (0 == target) {
            rss.add(new ArrayList<>(this.list));
            return;
        }

        // 跳过第i个数，一致到搜索树的叶子结点
        dfs(candidates, target, i + 1);
        // 要求target不小于0
        if (target - candidates[i] >= 0) {
            // 选择第i个数
            this.list.add(candidates[i]);
            // ！处理第i个数，因为可以重复选择，因此不用i+1
            // 重复选择第i个数达到目标后不选择第i个数
            dfs(candidates, target - candidates[i], i);
            // ！回溯，不选择第i个数
            this.list.remove(this.list.size() - 1);
        }
    }
}
