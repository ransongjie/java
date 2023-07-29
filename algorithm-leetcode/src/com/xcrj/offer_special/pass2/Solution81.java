package com.xcrj.offer_special.pass2;

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
     * - 深度优先搜索的过程中进行回溯和剪枝
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 从选择第0个数开始
        dfs(candidates,target,0);
        return rss;
    }

    private void dfs(int[] candidates, int target, int i) {
        if(i==candidates.length) return;
        // 找到target
        if(target==0) {
            rss.add(new ArrayList<>(list));
            return;
        }

        // xcrj 从叶子结点开始做选择
        dfs(candidates, target, i+1);
        // xcrj 是否能够继续选择
        if(target-candidates[i]>=0){
            // 选择第i个元素
            list.add(candidates[i]);
            dfs(candidates, target-candidates[i], i);
            // 回溯，不选择第i个元素
            list.remove(list.size()-1);
        }
    }
}
