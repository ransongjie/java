package com.xcrj.interview150.binary_search_tree;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree
 * 验证二叉搜索树
 * 严格二叉搜索树 左小 右大
 */
public class Solution02 {

    // [-2147483648] 使用long才能通过此用例
    long preValue = Long.MIN_VALUE;//记录前1个值

    /**
     * 递归 中序遍历
     */
    public boolean isValidBST(TreeNode root) {
        //空树是搜索二叉树
        if (root == null) return true;
        //左子树是不是搜索二叉树
        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.val <= preValue) {
            return false;
        } else {
            preValue = root.val;
        }

        //右边子树是不是搜索二叉树
        return isValidBST(root.right);
    }

    /**
     * 迭代 中序遍历
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> s = new Stack<>();
        long preValue = Long.MIN_VALUE;

        while (!s.isEmpty() || root != null) {
            if (root != null) {
                s.push(root);
                root = root.left;
            } else {
                root = s.pop();

                if (preValue >= root.val) return false;
                else {
                    preValue = root.val;
                }

                root = root.right;
            }
        }

        return true;
    }

    /**
     * 套路解法
     *
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        Info ans = aSearchTree(root);
        return ans == null ? true : ans.aSearchTree;
    }

    /**
     * 后续遍历
     *
     * @param root
     * @return
     */
    public Info aSearchTree(TreeNode root) {
        //0个结点
        if (root == null) return null;
        //左子树
        Info left = aSearchTree(root.left);
        //右子树
        Info right = aSearchTree(root.right);

        //当前结点
        int min = root.val;//默认值
        int max = root.val;//默认值
        boolean aSearchTree = true;//默认值
        if (left != null) {
            min = Math.min(min, left.minVal);
            max = Math.max(max, left.maxVal);
        }
        if (right != null) {
            min = Math.min(min, right.minVal);
            max = Math.max(max, right.maxVal);
        }
        //左子树不是搜索二叉树||左子树的最大值>=根结点的值
        if (left != null && (!left.aSearchTree || left.maxVal >= root.val)) {
            aSearchTree = false;
        }
        //右子树不是搜索二叉树||右子树的最小值<=根结点的值
        if (right != null && (!right.aSearchTree || right.minVal <= root.val)) {
            aSearchTree = false;
        }
        return new Info(aSearchTree, min, max);
    }
}
//公共信息
class Info {
    boolean aSearchTree;
    int minVal;
    int maxVal;

    public Info(boolean ast, int minVal, int maxVal) {
        this.aSearchTree = ast;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }
}
