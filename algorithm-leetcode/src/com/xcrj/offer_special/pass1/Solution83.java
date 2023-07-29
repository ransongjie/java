package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 083. 没有重复元素集合的全排列
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
 */
public class Solution83 {
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
     * 过程：
     * - 第一层，选择把那个数放到开头，第1个位置
     * - 第二层，选择把剩下的数中的那个数放到剩下元素的开头，第2个位置
     * - 第三层，选择把剩下的数中的那个数放到剩下元素的开头，第3个位置
     */
    public List<List<Integer>> permute1(int[] nums) {
        for (int num : nums) {
            this.list.add(num);
        }
        // 选择把剩下的数中的那个数放到剩下元素的开头，第1个位置，索引0
        backtrack(nums.length, 0);
        return rss;
    }

    /**
     * @param len   数组长度
     * @param first 剩下元素的开头下标
     */
    private void backtrack(int len, int first) {
        if (len == first) {
            this.rss.add(new ArrayList<>(this.list));
            return;
        }

        // 每一层尝试把剩余的每个元素放到开头位置
        for (int i = first; i < len; i++) {
            // 选择把剩下的数中的那个数放到剩下元素的开头，第first+1个位置，索引first位置
            Collections.swap(this.list, first, i);
            // 处理下一层的剩下元素的开头
            backtrack(len, first + 1);
            // 回溯，交换回来
            Collections.swap(this.list, first, i);
        }
    }
}
