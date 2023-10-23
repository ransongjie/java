package com.xcrj.pass2.dp.house_robber;

/**
 * https://leetcode.cn/problems/house-robber-iii/
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 二叉树房屋偷盗
 */
public class Main3 {

    /**
     * 暴力递归，超出执行时间
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        // 偷当前结点，不能偷左右结点
        int money = root.val;
        if (root.left != null) money += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) money += rob(root.right.left) + rob(root.right.right);
        // 不偷当前结点，可以偷左右结点
        return Math.max(money, rob(root.left) + rob(root.right));
    }

    /**
     * 树形动态规划
     *
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        int[] dp = f(root);
        return Math.max(dp[0], dp[1]);
    }

    /**
     * 后续遍历
     *
     * @param node
     * @return
     */
    private int[] f(TreeNode node) {
        //dp[0]偷当前结点，dp[1]不偷当前结点
        int[] dp = new int[2];
        if (node == null) return dp;
        int[] left = f(node.left);
        int[] right = f(node.right);
        // 偷当前结点，不能偷左右结点
        dp[0] += node.val + left[1] + right[1];
        // 不偷当前结点，可以偷左右结点
        dp[1] += Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return dp;
    }
}
