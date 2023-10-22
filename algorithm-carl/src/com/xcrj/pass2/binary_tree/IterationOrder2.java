package com.xcrj.pass2.binary_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 标记法 将访问结点和处理结点都入栈，处理结点入栈中紧跟1个null
 * 处理结点=根结点
 */
public class IterationOrder2 {
    static List<Integer> preOrder(Node node) {
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            //非标记
            if (stack.peek() != null) {
                // 入栈 右左根，出栈 根左右
                Node n = stack.pop();
                if (n.right != null) stack.push(n.right);
                if (n.left != null) stack.push(n.left);
                stack.push(n);
                stack.push(null);
            }
            //标记
            else {
                stack.pop();//弹出null
                ans.add(stack.pop().val);//处理结点
            }
        }

        return ans;
    }

    static List<Integer> inOrder(Node node) {
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            //非标记
            if (stack.peek() != null) {
                // 入栈 右根左，出栈 左根右
                Node n = stack.pop();
                if (n.right != null) stack.push(n.right);
                stack.push(n);
                stack.push(null);
                if (n.left != null) stack.push(n.left);
            }
            //标记
            else {
                stack.pop();//弹出null
                ans.add(stack.pop().val);//处理结点
            }
        }

        return ans;
    }

    static List<Integer> postOrder(Node node) {
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            //非标记
            if (stack.peek() != null) {
                // 入栈 根右左，出栈 左右根
                Node n = stack.pop();
                stack.push(n);
                stack.push(null);
                if (n.right != null) stack.push(n.right);
                if (n.left != null) stack.push(n.left);
            }
            //标记
            else {
                stack.pop();//弹出null
                ans.add(stack.pop().val);//处理结点
            }
        }

        return ans;
    }
}
