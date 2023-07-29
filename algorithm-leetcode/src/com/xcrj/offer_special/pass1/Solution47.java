package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 047. 二叉树剪枝
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 */
public class Solution47 {
    /**
     * 深度优先 后续遍历的过程中剪枝
     * 剪枝左右结点不存在且根结点值为0
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}
