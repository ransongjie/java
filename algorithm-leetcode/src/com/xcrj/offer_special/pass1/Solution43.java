package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 043. 往完全二叉树添加节点
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，并且所有的节点都尽可能地集中在左侧。
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 * - CBTInserter(TreeNode root)使用根节点为root的给定树初始化该数据结构；
 * - CBTInserter.insert(int v) 向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * - CBTInserter.get_root() 将返回树的根节点。
 */
public class Solution43 {

    /**
     * 使用队列记录可以插入新结点的父节点：倒数第2层没有右孩子的结点，最后1层结点
     * 先把到数第2层的结点插满，再考虑最后1层的结点
     * 广度优先遍历获取候选插入结点
     */
    class CBTInserter1 {
        Queue<TreeNode> candidateQue;
        TreeNode root;

        /**
         * 获取候选可插入父节点
         */
        public CBTInserter1(TreeNode root) {
            this.root = root;
            candidateQue = new ArrayDeque<>();

            // 广度优先遍历
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode p = queue.poll();
                if (p.left != null) queue.offer(p.left);
                if (p.right != null) queue.offer(p.right);
                if (!(p.left != null && p.right != null)) candidateQue.offer(p);
            }
        }

        /**
         * 插入新结点
         * - 没有插入的位置了才出栈
         * - 插入的新结点也是可再插入的候选结点
         */
        public int insert(int v) {
            TreeNode node = new TreeNode(v);
            // poll()会移除对头结点，peak()不会移除
            TreeNode p = candidateQue.peek();
            // 左孩子为null 插入成为左孩子
            if (p.left == null) {
                p.left = node;
            }
            // 左孩子存在&&右孩子为null 插入成为右孩子 没有插入的位置了 出栈p
            else if (p.right == null) {
                p.right = node;
                candidateQue.poll();
            }

            // 插入的新结点也是可再插入的候选结点
            candidateQue.offer(node);

            return p.val;
        }

        public TreeNode get_root() {
            return this.root;
        }
    }


    /**
     * 我们需要知道结点要插入到什么位置
     * - 观察这棵结点总数为10的完全二叉树（编号从1开始，每个结点编号的二进制表示已经给出）
     * - 发现结点编号的二进制位表示记录了它经历过的左右子树，0经历过左子树，1经历过右子树
     * <p>
     * 完全二叉树结点编号的二进制表示记录了结点经过的路径
     * - 结点i的左孩子编号为2*i(i的二进制表示末尾+0)，右孩子编号为2*i+1(i的二进制表示末尾+1)
     * - 左0右1
     */
    class CBTInserter2 {
        Queue<TreeNode> que;
        int sum;
        TreeNode root;

        /**
         * 使用根节点为root的给定树初始化该数据结构
         * <p>
         * 广度优先获取结点总数
         */
        public CBTInserter2(TreeNode root) {
            this.root = root;
            this.que = new ArrayDeque<>();
            this.sum = 0;
            que.offer(root);
            while (!que.isEmpty()) {
                TreeNode p = que.poll();
                sum++;
                if (p.left != null) que.offer(p.left);
                if (p.right != null) que.offer(p.right);
            }
        }

        /**
         * 向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，
         * 并返回插入的新节点的父节点的值
         */
        public int insert(int v) {
            // 结点编号为sum的结点的二进制表示记录了该结点经过的路径
            // 插入新结点，结点总数加1，结点编号加1
            sum++;
            // 获取sum的二进制占位数量
            // Integer.numberOfLeadingZeros(sum)：获取sum二进制位表示头部0的个数，比如 1有32-1个头部0 2有32-2个头部0,3有32-2个头部0
            int numbit = 32 - Integer.numberOfLeadingZeros(sum);

            TreeNode p = root;
            // 根据 sum的第2位~第n-1位的二进制表示决定移动方向 sum的二进制表示第i位为0左移 为1右移
            // i表示1左移的位数，比如 numbit=4时，需判断第2位~第3位的二进制表示，则1左移2位时，1对应第2位；1左移1位时，1对应第3位
            for (int i = numbit - 2; i >= 1; i--) {
                // 按位与 判断第i位是0还是1
                if ((sum & 1 << i) == 0) p = p.left;
                else p = p.right;
            }

            // 根据最后1位 第4位判断插入位置
            TreeNode node = new TreeNode(v);
            if ((sum & 1 << 0) == 0) p.left = node;
            else p.right = node;

            return p.val;
        }

        /**
         * 返回树的根节点
         */
        public TreeNode get_root() {
            return this.root;
        }
    }
}
