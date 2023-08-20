package com.xcrj.interview150.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 二叉树的层序遍历
 */
public class Solution11 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (null == root) return ans;

        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        List<Integer> list;
        while (!que.isEmpty()) {
            int size = que.size();
            TreeNode p = null;
            list = new ArrayList<>();
            // 一次操作一层结点
            while (size > 0) {
                p = que.poll();
                list.add(p.val);
                if (p.left != null) que.offer(p.left);
                if (p.right != null) que.offer(p.right);
                size--;
            }
            ans.add(list);
        }

        return ans;
    }
}
