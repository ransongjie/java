package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指 Offer II 055. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * - BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * - boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * - int next()将指针向右移动，然后返回指针处的数字。
 * <p>
 * 注意:
 * - 指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * - 可以假设next()调用总是有效的，也就是说，当调用 next()时，BST 的中序遍历中至少存在一个下一个数字。
 */
public class Solution55 {
    /**
     * 思路：深度优先中序遍历的结果存储到list<Integer>中，再操作list即可
     */
    class BSTIterator1 {
        int idx;
        List<Integer> list;

        public BSTIterator1(TreeNode root) {
            this.idx = 0;
            this.list = new ArrayList<>();
            dfs(root, list);
        }

        public int next() {
            return this.list.get(this.idx++);
        }

        public boolean hasNext() {
            return this.idx < this.list.size();
        }

        private void dfs(TreeNode root, List<Integer> list) {
            if (null == root) return;
            dfs(root.left, list);
            list.add(root.val);
            dfs(root.right, list);
        }
    }

    /**
     * 迭代式深度优先中序遍历
     */
    class BSTIterator2 {
        // 记录当前结点
        TreeNode cur;
        // 压栈结点
        Stack<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            this.cur = root;
            this.stack = new Stack<>();
        }

        public int next() {
            // 深度优先遍历使用while，next()只要下个结点不需要while遍历
//            while (!this.stack.isEmpty()) {
            // 左子树
            while (null != cur) {
                this.stack.push(cur);
                cur = cur.left;
            }
            // 出栈访问根
            cur = this.stack.pop();
            int r = cur.val;
            // 右子树
            cur = cur.right;
//            }

            return r;
        }

        public boolean hasNext() {
            // cur = cur.right;时，cur可能为null，同时stack可能非空
            // this.cur = root;时，cur是非null的，同时stack是空的
            return null != cur || !this.stack.isEmpty();
        }
    }
}
