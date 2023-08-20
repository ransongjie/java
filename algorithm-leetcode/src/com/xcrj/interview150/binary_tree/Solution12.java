package com.xcrj.interview150.binary_tree;

import java.util.*;

/**
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 * 二叉树的锯齿形层序遍历
 * 即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行
 */
public class Solution12 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (null == root) return ans;

        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        LinkedList<Integer> delist;//
        while (!que.isEmpty()) {
            int size = que.size();
            TreeNode p = null;
            delist = new LinkedList<>();
            // 一次操作一层结点
            while (size > 0) {
                p = que.poll();
                // 添加时改动顺序，偶数正序添加，奇数倒序添加
                if (ans.size() % 2 == 0) delist.addLast(p.val);
                else delist.addFirst(p.val);

                if (p.left != null) que.offer(p.left);
                if (p.right != null) que.offer(p.right);
                size--;
            }
            ans.add(delist);
        }

        return ans;
    }
}
