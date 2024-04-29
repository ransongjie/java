package com.xcrj.binary_tree;

//递归遍历
public class RecursionOrder {
    public void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node) {
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    public void lastOrder(Node node) {
        lastOrder(node.left);
        lastOrder(node.right);
        System.out.println(node.val);
    }
}
