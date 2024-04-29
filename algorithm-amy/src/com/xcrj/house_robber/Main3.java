package com.xcrj.house_robber;

/**
 * https://leetcode.cn/problems/house-robber-iii/
 * 二叉树房屋偷盗
 * <p>
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 */
public class Main3 {
    /**
     * 树形dp=树遍历+dp
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] dp = f(root);
        return Math.max(dp[0], dp[1]);
    }

    //后序遍历
    private int[] f(TreeNode node) {
        //dp[0]不偷当前结点，dp[1]偷当前结点
        int[] dp = new int[2];
        if (node == null) return dp;
        int[] left = f(node.left);
        int[] right = f(node.right);
        //不偷当前结点，看左子树最大值，看右子树最大值
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //偷当前结点，不可以偷左子树，不可以偷右子树
        dp[1] = node.val + left[0] + right[0];
        return dp;
    }

    public int rob2(TreeNode root) {
        if (root == null) return 0;
        int ans1 = root.val;
        //偷当前结点，可以偷左左子树，可以偷左右子树，可以偷右右子树，可以偷右左子树
        if (root.left != null) {
            ans1 += rob2(root.left.left) + rob2(root.left.right);
        }
        if (root.right != null) {
            ans1 += rob2(root.right.right) + rob2(root.right.left);
        }
        //不偷当前结点，可以偷左子树，可以偷右子树
        int ans2 = rob2(root.left) + rob2(root.right);
        return Math.max(ans1, ans2);
    }
}
