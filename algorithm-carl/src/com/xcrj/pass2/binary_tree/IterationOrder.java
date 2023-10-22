package com.xcrj.pass2.binary_tree;

import java.util.Stack;

/**
 * 迭代 前 中 后 序遍历
 */
public class IterationOrder {
    static void preOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.println(n.val);
            // 先右再左
            if (n.right != null) stack.push(n.right);
            if (n.left != null) stack.push(n.left);
        }
    }

    static void inOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null && !stack.isEmpty()) {
            // 一直找左
            if (node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
            //找不到左了，先找右再找左
            else {
                node = stack.pop();
                System.out.println(node.val);
                node = node.right;
            }
        }
    }

    // 根左右》根右左》左右根
    static void postOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            // System.out.println(n.val);
            stack2.push(n);//push代替sout
            // 先左再右
            if (n.left != null) stack.push(n.left);
            if (n.right != null) stack.push(n.right);
        }

        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }
}
