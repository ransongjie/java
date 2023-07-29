package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 052. 展平二叉搜索树
 * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 */
public class Solution52 {
    /**
     * 按照中序遍历存储二叉树的值到链表中
     * 遍历链表重建二叉树
     */
    public TreeNode increasingBST1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // 尾插法
        // 虚伪头结点统一 list中有无元素的情况
        TreeNode dummy = new TreeNode(-1);
        TreeNode p = dummy;
        for (Integer val : list) {
            TreeNode node = new TreeNode(val);
            p.right = node;
            p = node;
        }

        return dummy.right;
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (null == root) return;

        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    // 尾插法需要有个指针指向尾部结点。记录重新连接的二叉树的尾结点
    TreeNode p;

    /**
     * 在中序遍历的过程中改变结点的指向
     */
    public TreeNode increasingBST2(TreeNode root) {
        // 虚伪头结点统一二叉树中有无元素的情况
        TreeNode dummy = new TreeNode(-1);
        p = dummy;
        dfs2(root);
        return dummy.right;
    }

    public void dfs2(TreeNode root) {
        if (null == root) return;

        dfs2(root.left);
        // 新结点连接到右子树
        p.right = root;
        // 新建二叉树没有左结点
        root.left = null;
        p = root;
        dfs2(root.right);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        n5.left = n3;
        n5.right = n6;
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;
        n6.right = n8;
        n8.left = n7;
        n8.right = n9;
        Solution52 solution52 = new Solution52();
        solution52.increasingBST1(n5);
    }
}
