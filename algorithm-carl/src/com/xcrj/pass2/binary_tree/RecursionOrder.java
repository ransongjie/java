package com.xcrj.pass2.binary_tree;

/**
 * 递归 前序遍历 中序遍历 后续遍历
 */
public class RecursionOrder {
    static void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    static void inOrder(Node node) {
        if (node == null) return;
        preOrder(node.left);
        System.out.println(node.val);
        preOrder(node.right);
    }

    static void postOrder(Node node) {
        if (node == null) return;
        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.val);
    }
}
