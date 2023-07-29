package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 044. 二叉树每层的最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */
public class Solution44 {
    /**
     * 前序遍历（根左右） 深度优先
     * - 到达新的层直接往List<层最大值>[层高]=存储值
     * - 不是新的层Max(List<层最大值>[层高], p.val)
     */
    public List<Integer> largestValues1(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> rs = new ArrayList<>();
        dfs(root, rs, 0);
        return rs;
    }

    /**
     * 前序遍历（根左右） 深度优先
     */
    public void dfs(TreeNode root, List<Integer> maxPerLevel, int numLevel) {
        // 相关则表示新到达了一层
        if (numLevel == maxPerLevel.size()) {
            maxPerLevel.add(root.val);
        }
        // 没有新到达1层，达到了同层的另一个结点，比较获取最大值
        else {
            int numLevelMaxV = Math.max(maxPerLevel.get(numLevel), root.val);
            maxPerLevel.set(numLevel, numLevelMaxV);
        }
        // 左孩子下一层
        if (null != root.left) dfs(root.left, maxPerLevel, numLevel + 1);
        // 右孩子下一层
        if (null != root.right) dfs(root.right, maxPerLevel, numLevel + 1);
    }

    /**
     * 广度优先遍历
     * - 每次将同层的所有结点全部出队，将孩子结点全部入队
     * - 出队所有结点即出队que.size()个结点
     */
    public List<Integer> largestValues2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> rs = new ArrayList<>();
        Queue<TreeNode> que = new ArrayDeque<>();
        TreeNode p = root;
        que.offer(p);
        while (!que.isEmpty()) {
            int currentLevMax = Integer.MIN_VALUE;
            // 将队列中的所有元素出队列
            int size = que.size();
            while (size > 0) {
                size--;
                p = que.poll();
                currentLevMax = Math.max(currentLevMax, p.val);
                if (null != p.left) que.offer(p.left);
                if (null != p.right) que.offer(p.right);
            }
            rs.add(currentLevMax);
        }

        return rs;
    }
}
