package com.xcrj.binary_tree;

/**
 * 递归遍历
 * 递归
 * 出口 入参 出参 函数体
 */
public class RecursionOrder {
     void preOrder(BTreeNode node) {
        if (node == null) return;
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

     void inOrder(BTreeNode node) {
        if (node == null) return;
        preOrder(node.left);
        System.out.println(node.val);
        preOrder(node.right);
    }

     void lstOrder(BTreeNode node) {
        if (node == null) return;
        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.val);
    }
}
