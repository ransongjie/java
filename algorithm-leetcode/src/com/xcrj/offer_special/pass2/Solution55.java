package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指 Offer II 055. 二叉搜索树迭代器
 */
public class Solution55 {
    /**
     * 深度优先中序遍历结果转储到list中，再操作list
     */
    class BSTIterator1 {
        List<Integer> list;
        int idx;

        public BSTIterator1(TreeNode root) {
            list = new ArrayList<>();
            idx = 0;
            dfs(root, list);
        }

        public int next() {
            return list.get(idx++);
        }

        public boolean hasNext() {
            return idx < list.size();
        }

        private void dfs(TreeNode root, List<Integer> list) {
            if (root == null)
                return;

            dfs(root.left, list);
            list.add(root.val);
            dfs(root.right, list);
        }
    }

    /**
     * 迭代式深度优先中序遍历
     */
    class BSTIterator2 {
        TreeNode cur;
        Stack<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            cur = root;
            stack = new Stack<>();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 中序遍历 返回根
            cur = stack.pop();
            int r = cur.val;
            // 记录下次的起点
            cur = cur.right;

            return r;
        }

        public boolean hasNext() {
            // xcrj 迭代式深度优先
            return cur != null || !stack.isEmpty();
        }
    }
}
