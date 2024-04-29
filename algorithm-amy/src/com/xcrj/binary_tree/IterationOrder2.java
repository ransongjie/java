package com.xcrj.binary_tree;

import java.util.Stack;

/**
 * 统一迭代遍历
 * 标记法
 * 将访问结点和处理结点都入栈，处理结点入栈中紧跟1个null
 */
public class IterationOrder2 {
    public void preOrder(Node node) {
        Stack<Node> s = new Stack<>();
        s.push(node);
        while (!s.isEmpty()) {
            if (s.peek() != null) {
                //入栈 右左根》出栈 根左右
                Node n = s.pop();
                if (n.right != null) {
                    s.push(n.right);
                }
                if (n.left != null) {
                    s.push(n.left);
                }
                s.push(n);//
                s.push(null);//空标记入栈
            } else {
                s.pop();//出栈空标记
                System.out.println(s.pop().val);
            }
        }
    }

    public void inOrder(Node node) {
        Stack<Node> s = new Stack<>();
        s.push(node);
        while (!s.isEmpty()) {
            if (s.peek() != null) {
                //入栈 右根左》出栈 左根右
                Node n = s.pop();
                if (n.right != null) {
                    s.push(n.right);
                }
                s.push(n);
                s.push(null);//空标记紧跟当前结点
                if (n.left != null) {
                    s.push(n.left);
                }
            } else {
                s.pop();//出栈空标记
                System.out.println(s.pop().val);
            }
        }
    }

    public void lastOrder(Node node) {
        Stack<Node> s = new Stack<>();
        s.push(node);
        while (!s.isEmpty()) {
            if (s.peek() != null) {
                //入栈 根右左 》出栈 左右根
                Node n = s.pop();
                s.push(n);
                s.push(null);
                if (n.right != null) {
                    s.push(n.right);
                }
                if (n.left != null) {
                    s.push(n.left);
                }
            } else {
                s.pop();
                System.out.println(s.pop().val);
            }
        }
    }
}
