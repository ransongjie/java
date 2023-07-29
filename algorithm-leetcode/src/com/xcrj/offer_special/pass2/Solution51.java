package com.xcrj.offer_special.pass2;
/**
 * 剑指 Offer II 051. 节点之和最大的路径
 * - 结点只能经过1次
 * - 可以从子结点到父结点
 */
public class Solution51 {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }
    
    /**
     * 深度优先 后序遍历
     * - 最大路径和=当前结点的贡献+左孩子的最大贡献值+右孩子的最大贡献值
     * - 当前结点的最大贡献值=当前结点的贡献+Max(左孩子的最大贡献值,右孩子的最大贡献值)
     * - 当前结点最大贡献值， 每个结点只能经过1次 左》根 或 根》右
     * @param root
     * @return
     */
    public int dfs(TreeNode root) {
        // 空结点贡献值为0
        if(root==null)return 0;

        int maxLeft=Math.max(dfs(root.left),0);
        int maxRight=Math.max(dfs(root.right),0);
        // 当前结点最大路径和，左》根》右
        int pMaxSum=root.val+maxLeft+maxRight;
        // 二叉树的最大路径和
        maxSum=Math.max(maxSum,pMaxSum);
        // 当前结点最大贡献值， 每个结点只能经过1次 左》根 或 根》右
        return root.val+Math.max(maxLeft,maxRight);
    }
}
