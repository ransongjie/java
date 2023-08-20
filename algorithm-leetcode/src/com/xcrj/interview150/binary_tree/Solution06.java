package com.xcrj.interview150.binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-search-tree-iterator
 * 二叉搜索树迭代器
 * 实现二叉搜索树的升序迭代器
 * <p>
 * 输入: ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出: [null, 3, 7, true, 9, true, 15, true, 20, false]
 */
public class Solution06 {

}

/**
 * 先中序遍历存储到队列中
 */
class BSTIterator {
    Queue<TreeNode> que;

    public BSTIterator(TreeNode root) {
        que = new LinkedList<>();
        inOrder(root);
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        que.offer(root);
        inOrder(root.right);
    }

    public int next() {
        return que.poll().val;
    }

    public boolean hasNext() {
        return que.size() > 0;
    }
}

//中序遍历非递归形式
class BSTIterator2 {
    Stack<TreeNode> stack;

    public BSTIterator2(TreeNode root) {
        stack = new Stack<>();
        // 入栈所有左结点
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        //出栈，遍历左子树到底
        TreeNode cur = stack.pop(), p = cur.right;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        return cur.val;
    }

    public boolean hasNext() {
        return stack.size() > 0;
    }
}