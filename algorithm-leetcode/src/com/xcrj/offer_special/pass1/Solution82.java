package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 082. 含有重复元素集合的组合
 * 给定一个可能有重复数字的整数数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
 * <p>
 * 分析
 * - 已知整数数组candidates中含有重复的数字
 * - 要求解集不能包含重复的组合，相同的数字放到不同的位置认为是同一个组合
 */
public class Solution82 {
    List<int[]> numTimes = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    List<List<Integer>> rss = new ArrayList<>();

    /**
     * 回溯法
     * - 确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     * 解空间树
     * 剪枝函数
     * - 约束函数：剪去不满足约束条件的子树
     * - 限界函数：剪去得不到需要解（无解，非最优解）的子树
     * <p>
     * 根据分析
     * - 记录每个元素出现的次数
     * - 对于重复的数字一次性处理完毕，相当于把重复的数字在解集中放到一起，这样解集就不会包含重复的组合
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        /**
         * 记录每个元素出现的次数
         * 由于已经排过序，前面的数<=后面的数，遍历的当前数在numTimes末尾或还未添加到numTimes中
         */
        for (int c : candidates) {
            if (this.numTimes.isEmpty()) {
                this.numTimes.add(new int[]{c, 1});
                continue;
            }

            if (c != this.numTimes.get(this.numTimes.size() - 1)[0]) {
                this.numTimes.add(new int[]{c, 1});
                continue;
            }

            ++this.numTimes.get(this.numTimes.size() - 1)[1];
        }
        // 0从索引0开始
        dfs(0, target);

        return rss;
    }

    private void dfs(int i, int target) {
        // 找到1个组合
        if (target == 0) {
            this.rss.add(new ArrayList<>(list));
            return;
        }
        // 剪枝函数
        // numTimes中无元素
        if (i == this.numTimes.size()) return;
        // target比numsTimes中最小的值还小，则找不到组合了
        if (target < this.numTimes.get(i)[0]) return;

        // 不选择当前元素
        dfs(i + 1, target);
        // 选择重复元素k次
        // 综合考虑 实际上根据target我能选择某个元素k次 和 这个元素出现的次数
        int k = Math.min(target / this.numTimes.get(i)[0], this.numTimes.get(i)[1]);
        for (int j = 1; j <= k; j++) {
            // 选择重复元素k次
            this.list.add(this.numTimes.get(i)[0]);
            // 处理下1个元素。j*值，已经添加的元素值需要从target中减去
            dfs(i + 1, target - j * this.numTimes.get(i)[0]);
        }
        // 选择k次需要回溯k次
        for (int j = 1; j <= k; j++) {
            this.list.remove(this.list.size() - 1);
        }
    }
}
