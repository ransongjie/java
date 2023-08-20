package com.xcrj.interview150.binary_tree;

/**
 * https://leetcode.cn/problems/sum-root-to-leaf-numbers
 * 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 */
public class Solution05 {
    /**
     * 第1层*0, 第2层*10
     * 每往下一层*10
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return f(root, 0);
    }

    private int f(TreeNode root, int v) {
        if (root == null) return 0;
        v = v * 10 + root.val;
        if (root.left == null && root.right == null) {
            return v;
        }
        // 左子树值 + 右子树值
        return f(root.left, v) + f(root.right, v);
    }
}
