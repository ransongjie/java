package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 051. 节点之和最大的路径
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。
 * <p>
 * 结点只能经过1次
 * 可以从子结点到父结点
 */
public class Solution51 {
    // 记录最大路径和
    int maxSum = Integer.MIN_VALUE;

    /**
     * 深度优先前序遍历，从叶子结点向上累积
     * - 当前结点构成的最大路径和=当前结点的贡献+左孩子的最大贡献值+右孩子的最大贡献值
     * - 当前结点的最大贡献值=当前结点的贡献+Max(左孩子的最大贡献值,右孩子的最大贡献值)。因为从当前结点回退到其父节点，要求每个结点只经过1次，如果左右孩子都取到，不能保证每个结点只经过1次
     * <p>
     * - 最大贡献值：结点值为负则取0
     * - 空结点：最大贡献值=0
     * - 非空结点：最大贡献值=当前结点的贡献+Max(左孩子的最大贡献值,右孩子的最大贡献值)
     */
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return this.maxSum;
    }

    /**
     * 递归计算每个结点构成的最大路径和：深度优先 前序遍历
     */
    public int dfs(TreeNode root) {
        if (null == root) return 0;

        // 前序遍历
        int maxGainL = Math.max(dfs(root.left), 0);
        int maxGainR = Math.max(dfs(root.right), 0);
        // 当前结点构成的最大路径和=当前结点的贡献+左孩子的最大贡献值+右孩子的最大贡献值
        int pMaxSum = root.val + maxGainL + maxGainR;
        // 记录最大路径和
        this.maxSum = Math.max(this.maxSum, pMaxSum);

        // 当前结点的最大贡献值=当前结点的贡献+Max(左孩子的最大贡献值,右孩子的最大贡献值)
        return root.val + Math.max(maxGainL, maxGainR);
    }

}
