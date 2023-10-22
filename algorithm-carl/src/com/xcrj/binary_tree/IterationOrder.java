package com.xcrj.binary_tree;

import java.util.Stack;

/**
 * 迭代遍历
 */
public class IterationOrder {
    void preOrder(BTreeNode node) {
        Stack<BTreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BTreeNode n = stack.pop();
            System.out.println(n.val);
            // 先右再左
            if (n.right != null) stack.push(n.right);
            if (n.left != null) stack.push(n.left);
        }
    }

    void inOrder(BTreeNode node) {
        Stack<BTreeNode> stack = new Stack<>();
        BTreeNode cur = node;
        while (cur != null && !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                // 一直找左
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.println(cur.val);
                // 左为空找右
                cur = cur.right;
            }
        }
    }

    //根左右》根右左》左右根
    void lstOrder(BTreeNode node) {
        // 两个栈
        Stack<BTreeNode> stack = new Stack<>();
        Stack<BTreeNode> stack2 = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BTreeNode n = stack.pop();
            stack2.push(n);
            // 先左再右
            if (n.left != null) stack.push(n.left);
            if (n.right != null) stack.push(n.right);
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }
}
