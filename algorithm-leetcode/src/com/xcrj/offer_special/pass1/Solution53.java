package com.xcrj.offer_special.pass1;

import java.util.Stack;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 * <p>
 * 二叉搜索树 按中序遍历出的结果是递增的序列
 */
public class Solution53 {
    /**
     * 深度优先 中序遍历
     * 双指针：如果pre=p 则cur就是p的后继结点
     * - pre 记录前一个访问的结点
     * - cur 记录当前访问的结点
     * <p>
     * 不使用递归深度优先的原因：找到p结点的后继就返回退出程序。递归深度优先return后不能退出程序，只是退出了当前递归栈
     */
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        if (null == root) return null;
        TreeNode pre = null;
        TreeNode cur = root;
        // 迭代栈式 深度优先中序遍历
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            // 左子树
            while (null != cur) {
                stack.push(cur);
                cur = cur.left;
            }
            // 访问根结点
            cur = stack.pop();
            if (p == pre) {
                return cur;
            } else {
                pre = cur;
            }
            // 右子树
            cur = cur.right;
        }

        return null;
    }

    /**
     * 利用二叉搜索树的性质进行分治
     * 性质：二叉搜索树 按中序遍历出的结果是递增的序列
     * 分治：
     * - p的右结点存在，则找p右结点的最左结点
     * - p的右结点不存在，找大于p的最左结点（左斜树），小于p的最右边结点（右斜树）
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        // p的右结点存在，则找p右结点的最左结点
        if (null != p.right) {
            successor = p.right;
            while (null != successor.left) {
                successor = successor.left;
            }
            return successor;
        }
        // p的右结点不存在，找大于p的最左结点（左斜树），小于p的最右边结点（右斜树）
        TreeNode cur = root;
        while (null != cur) {
            // cur.val>p.val，走左子树
            if (cur.val > p.val) {
                successor = cur;
                cur = cur.left;
            }
            // cur.val<=p.val，走右子树
            else {
                cur = cur.right;
            }
        }
        return successor;
    }
}
