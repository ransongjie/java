package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 080. 含有 k 个元素的组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class Solution80 {
    List<List<Integer>> rss = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 回溯法
     * - 确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     * 解空间树
     * - 子集树：达到要求的子集，元素子集。选择某个元素/不选择某个元素
     * 剪枝函数
     * - 约束函数：剪去不满足约束条件的子树
     * - 限界函数：剪去得不到需要解（无解，非最优解）的子树
     */
    public List<List<Integer>> combine1(int n, int k) {
        // 从cur=1开始
        dfs(n, k, 1);
        return rss;
    }

    /**
     * @param n   1~n的数
     * @param k   从1~n选择k个数
     * @param cur 当前选择的数
     */
    private void dfs(int n, int k, int cur) {
        // 剪枝函数/限界函数。已选择的元素数量+未选择的元素总量<k
        if (list.size() + (n - cur + 1) < k) return;

        // 已经选择了k个数
        if (list.size() == k) {
            this.rss.add(new ArrayList<>(list));
            return;
        }


        /**
         *  cur不能大于n
         *  这个if可以合并到前面两个if中
         *  1. 当n>k时，cur=n+1时，list.size()<k||list.size()==k，总有1个会返回
         *  2. 当n<k时，第1个if会返回
         */
        if (cur > n) return;

        // ！选择cur这个数
        list.add(cur);
        // 处理第i+1个数
        dfs(n, k, cur + 1);
        // ！回溯，不选择cur这个数
        list.remove(list.size() - 1);
        // 再处理第i+1个数
        dfs(n, k, cur + 1);
    }
}
