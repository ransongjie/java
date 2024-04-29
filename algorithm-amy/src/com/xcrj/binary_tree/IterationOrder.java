package com.xcrj.binary_tree;

import java.util.Stack;

//迭代遍历
public class IterationOrder {
    //入栈 右左》出栈 左右
    public void preOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.println(n.val);
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
    }

    //一直找左，左为空找右
    public void inOrder(Node node) {
        Stack<Node> s = new Stack<>();
        //node!=null
        while (node != null && !s.isEmpty()) {
            if (node != null) {
                s.push(node);
                node = node.left;
            } else {
                Node n = s.pop();
                System.out.println(n.val);
                node = n.right;
            }
        }
    }

    //根左右》根右左》左右根
    public void lastOrder(Node node) {
        //2个栈
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(node);
        while (!s1.isEmpty()) {
            Node n = s1.pop();
            s2.push(n);
            //先左再右
            if (n.left != null) {
                s1.push(n.left);
            }
            if (n.right != null) {
                s1.push(n.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.println(s2.pop().val);
        }
    }
}
