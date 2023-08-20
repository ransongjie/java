package com.xcrj.interview150.binary_tree;

/**
 * https://leetcode.cn/problems/count-complete-tree-nodes
 * 完全二叉树的节点个数
 */
public class Solution07 {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 利用完全二叉树层数与结点个数的关系
     *
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        return f(root);
    }

    private int f(TreeNode root) {
        if (root == null) return 0;
        // leftLevel>=rightLevel
        int leftLevel = level(root.left);
        int rightLevel = level(root.right);
        // 左子树是满二叉树，左子树结点总数=2^leftLevel-1+1，继续操作右子树
        if (leftLevel == rightLevel) {
            return (1 << leftLevel) + f(root.right);
        }
        // rightLevel<leftLevel，右子树是满二叉树，右子树结点总数=2^rightLevel-1+1，继续操作左子树
        else {
            return (1 << rightLevel) + f(root.left);
        }
    }

    private int level(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;//
        }
        return level;
    }
}
