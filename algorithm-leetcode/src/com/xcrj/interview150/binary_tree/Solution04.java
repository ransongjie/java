package com.xcrj.interview150.binary_tree;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list
 * <p>
 * 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
public class Solution04 {
    public void flatten(TreeNode root) {
        while (root != null) {
            //没有左子树，直接考虑下一个结点
            if (root.left == null) {
                root = root.right;
            } else {
                //左子树最右边的结点
                TreeNode p = root.left;
                while (p.right != null) {
                    p = p.right;
                }
                //左子树最右结点 连接 原右子树
                p.right = root.right;
                //左子树放到原右子树位置
                root.right = root.left;
                root.left = null;
                //继续
                root = root.right;
            }
        }
    }

    /**
     * 利用先序遍历的代码，每遍历一个节点，就将上一个节点的右指针更新为当前节点。
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * 我们把 1 的右指针指向 2，那么 1 的原本的右孩子就丢失了
     * <p>
     * null <- 6 <- 5 <- 4 <- 3 <- 2 <- 1
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        f(root);
    }
    TreeNode pre=null;
    public void f(TreeNode cur) {
        if (cur == null) return;
        f(cur.right);
        f(cur.left);
        cur.right=pre;
        cur.left=null;
        pre=cur;
    }
}
