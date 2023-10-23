package com.xcrj.pass2.dp.basic;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/
 * 不同的二叉搜索树
 */
public class Main6 {
    /**
     * 二叉搜索树，左小右大
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // dp数组
        // dp[i]=以1为根节点的二叉搜索树数量+以2为根节点的二叉搜索树数量+...+以i为根节点的二叉搜索树数量
        int[] dp = new int[n + 1];
        // 初始状态
        dp[0] = 1;//空结点也认为是二叉搜索树
        // 状态转移
        /**
         * dp[3]
         * =以1为根节点的二叉搜索树数量+以2为根节点的二叉搜索树数量+以3为根节点的二叉搜索树数量
         * = 以1为根节点 左子树0个结点的二叉搜索树数量*右子树2个结点的二叉搜索树数量
         *  +以2为根节点 左子树1个结点的二叉搜索树数量*右子树1个结点的二叉搜索树数量
         *  +以2为根节点 左子树1个结点的二叉搜索树数量*右子树1个结点的二叉搜索树数量
         * =dp[0]*dp[2]+dp[1]*dp[1]+dp[2]*dp[0]
         * +=dp[j-1]*dp[i-j]
         */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        // 结果
        return dp[n];
    }
}
