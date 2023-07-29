package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 045. 二叉树最底层最左边的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 * <p>
 * 最底层 最左边 节点，可能是左结点或右结点
 */
public class Solution45 {

    int height;
    int val;

    /**
     * 深度优先
     * - 后序遍历（左右根）
     * - 看左右子树那个的高度更大
     */
    public int findBottomLeftValue1(TreeNode root) {
        this.height = 0;
        this.val = root.val;
        dfs(root, 0);
        return this.val;
    }

    public void dfs(TreeNode root, int height) {
        if (root == null) return;

        height++;
        // 后序遍历
        dfs(root.left, height);
        dfs(root.right, height);
        // 当前高度比我记录的高度更高 更新val
        if (height > this.height) {
            this.height = height;
            this.val = root.val;
        }
    }

    /**
     * 广度优先先入对右结点
     * 这样最后出队的一定是最下层最左侧结点
     */
    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> que = new ArrayDeque<>();
        TreeNode p = root;
        que.offer(p);
        while (!que.isEmpty()) {
            p = que.poll();
            if (null != p.right) que.offer(p.right);
            if (null != p.left) que.offer(p.left);
        }
        return p.val;
    }
}
