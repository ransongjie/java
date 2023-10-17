package com.xcrj.binary_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 统一迭代遍历
 * 标记法 将访问结点和处理结点都入栈，处理结点入栈中紧跟1个null
 */
public class IterationOrder2 {
    public void preOrder(BTreeNode node) {
        if (node == null) return;
        List<Integer> ans = new ArrayList<>();
        Stack<BTreeNode> stack = new Stack<>();
        stack.push(node);
        BTreeNode cur = null;
        while (!stack.isEmpty()) {
            if (stack.peek() != null) {
                cur = stack.pop();
                // ！！！入栈 右左根，出栈，根左右
                //访问结点入栈
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
                //！！！后入栈则先出栈
                //处理结点入栈
                stack.push(cur);
                //空标记入栈
                stack.push(null);
            } else {
                //空标记出栈
                stack.pop();
                //处理结点出栈
                cur = stack.pop();
                ans.add(cur.val);
            }
        }
    }

    public void inOrder(BTreeNode node) {
        if (node == null) return;
        List<Integer> ans = new ArrayList<>();
        Stack<BTreeNode> stack = new Stack<>();
        stack.push(node);
        BTreeNode cur = null;
        while (!stack.isEmpty()) {
            if (stack.peek() != null) {
                cur = stack.pop();
                // ！！！入栈 右根左，出栈，左根右
                //访问结点入栈
                if (cur.right != null) stack.push(cur.right);
                //处理结点入栈
                stack.push(cur);
                //空标记入栈
                stack.push(null);
                if (cur.left != null) stack.push(cur.left);
            } else {
                //空标记出栈
                stack.pop();
                //处理结点出栈
                cur = stack.pop();
                ans.add(cur.val);
            }
        }
    }

    public void lstOrder(BTreeNode node) {
        if (node == null) return;
        List<Integer> ans = new ArrayList<>();
        Stack<BTreeNode> stack = new Stack<>();
        stack.push(node);
        BTreeNode cur = null;
        while (!stack.isEmpty()) {
            if (stack.peek() != null) {
                cur = stack.pop();
                // ！！！入栈 根右左，出栈，左右根
                //处理结点入栈
                stack.push(cur);
                //空标记入栈
                stack.push(null);
                //访问结点入栈
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            } else {
                //空标记出栈
                stack.pop();
                //处理结点出栈
                cur = stack.pop();
                ans.add(cur.val);
            }
        }
    }
}
