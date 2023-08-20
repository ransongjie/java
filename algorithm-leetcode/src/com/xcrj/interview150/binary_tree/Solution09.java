package com.xcrj.interview150.binary_tree;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree
 * 二叉树的最近公共祖先
 */
public class Solution09 {
    /**
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode o1, TreeNode o2) {
        if (root == null || root == o1 || root == o2) {
            return root;
        }
        // 从root结点往左往右能否找到o1或o2结点，既找到了o1又找到了o2，则root就是o1和o2的最低公共祖先
        // 从root结点往左找到了o1&&往右没找到o2，则o1是o2的祖先
        TreeNode left = lowestCommonAncestor(root.left, o1, o2);
        TreeNode right = lowestCommonAncestor(root.right, o1, o2);
        if (left != null && right != null) {
            return root;
        }

        //o1是o2的祖先，或o2是o1的祖先
        return left != null ? left : right;
    }
}
