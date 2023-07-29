package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 049. 从根节点到叶节点的路径数字之和
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 本质：
 * - 高度为3的二叉树，第一层根节点的值=根节点数字*100=A  第二层1个结点的值=A+数字*10=B 第三层1个结点的值=B+数字=C
 * - 子结点sum=父节点sum*10+子节点值，到叶子结点结束
 */
public class Solution49 {
    /**
     * 深度优先
     */
    public int sumNumbers1(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * 下层结点之和=上层结点之和*10+下层结点值
     */
    public int dfs(TreeNode root, int preSum) {
        if (null == root) return 0;

        // 子结点sum=父节点sum*10+子节点值
        int sum = preSum * 10 + root.val;
        // 到叶子结点结束
        if (null == root.left && null == root.right) {
            return sum;
        } else {
            // 未到叶子结点
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    /**
     * 广度优先遍历
     * - 左右孩子不存在返回结果
     * - 左孩子或者右边孩子存在 childSum=parentSum*10+childVal
     * <p>
     * 两个队列：
     * - 保存结点
     * - 保存结点对应的sum
     * <p>
     * 到叶子结点结束
     */
    public int sumNumbers2(TreeNode root) {
        if (null == root) return 0;

        Queue<TreeNode> tQue = new ArrayDeque<>();
        Queue<Integer> sumQue = new ArrayDeque<>();
        tQue.offer(root);
        sumQue.offer(root.val);
        // 记录 到叶子结点的和 的和
        int sum = 0;

        while (!tQue.isEmpty()) {
            TreeNode p = tQue.poll();
            int pSum = sumQue.poll();
            TreeNode pl = p.left, pr = p.right;
            // 到叶子结点结束
            if (null == pl && null == pr) {
                sum += pSum;
            } else {
                if (null != pl) {
                    tQue.offer(pl);
                    sumQue.offer(pSum * 10 + pl.val);
                }
                if (null != pr) {
                    tQue.offer(pr);
                    sumQue.offer(pSum * 10 + pr.val);
                }
            }
        }

        return sum;
    }
}
