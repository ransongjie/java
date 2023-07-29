package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 056. 二叉搜索树中两个节点之和
 * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
 */
public class Solution56 {

    /**
     * 深度优先遍历+hash表
     * - 遍历过程中将val存储到hash表中
     * - 遍历过程中查看hash表中是否存在k-val
     * <p>
     * 深度优先前序遍历：
     * - 访问根节点
     * - 递归左子结点
     * - 递归右子结点
     */
    Set<Integer> set = new HashSet<>();

    public boolean findTarget1(TreeNode root, int k) {
        if (null == root) return false;
        // 遍历过程中查看hash表中是否存在k-val
        if (this.set.contains(k - root.val)) return true;
        // 遍历过程中将val存储到hash表中
        this.set.add(root.val);
        // ！只要1个为true的情况可以使用||或的写法
        // 只要其中有1个为true即可
        return findTarget1(root.left, k) || findTarget1(root.right, k);
    }

    /**
     * 广度优先遍历+hash表
     * - 遍历过程中将val存储到hash表中
     * - 遍历过程中查看hash表中是否存在k-val
     * <p>
     * 广度优先遍历
     * - 出队访问
     * - 左子结点非空加入队列
     * - 右子结点非空加入队列
     */
    public boolean findTarget2(TreeNode root, int k) {
        if (null == root) return false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 出队访问
            TreeNode node = queue.poll();
            if (set.contains(k - node.val)) return true;
            set.add(node.val);
            // 左子结点非空加入队列
            if (null != node.left) queue.offer(node.left);
            // 右子结点非空加入队列
            if (null != node.right) queue.offer(node.right);
        }
        return false;
    }

    /**
     * 深度优先中序遍历的结果序列是升序的
     * 对结果序列使用双指针
     * - left指针指向左边
     * - right指针指向右边
     * - left+right 值之和>k，right左移，值之和<k，left右移，left指针==right指针还没有找到结果，则返回false
     */
    public boolean findTarget3(TreeNode root, int k) {
        if (null == root) return false;
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            if (k == list.get(l) + list.get(r)) return true;
            if (k > list.get(l) + list.get(r)) l++;
            else r--;
        }
        return false;
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (null == root) return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    /**
     * 迭代式深度优先使用 栈记录双指针遍历的路径
     * - leftStack记录left指针迭代遍历的路径，leftStack栈顶到底存储 中序遍历序列从左往右的值 递增值
     * - rightStack记录right指针迭代遍历的路径，rightStack栈顶到底存储 中序遍历序列从右往左的值 递减值
     */
    public boolean findTarget4(TreeNode root, int k) {
        if (null == root) return false;

        // leftStack记录left指针迭代遍历的路径
        Stack<TreeNode> sL = new Stack<>();
        // rightStack记录right指针迭代遍历的路径
        Stack<TreeNode> sR = new Stack<>();

        // 左指针不为空则一直往左遍历
        TreeNode l = root;
        sL.push(l);
        while (null != l.left) {
            sL.push(l.left);
            l = l.left;
        }

        // 右指针不为空则一直往右遍历
        TreeNode r = root;
        sR.push(r);
        while (null != r.right) {
            sR.push(r.right);
            r = r.right;
        }

        // 判等
        while (l != r) {
            if (k == l.val + r.val) return true;
            // 移动左指针
            if (k > l.val + r.val) l = getLeft(sL);
                // 移动右指针
            else r = getRight(sR);
        }
        return false;
    }

    /**
     * 目的：将小于root.val的结点入栈，右孩子的左子树都小于右孩子
     * 出栈结点右孩子存在左子树则入栈左子树中结点
     */
    public TreeNode getLeft(Stack<TreeNode> sL) {
        TreeNode root = sL.pop();
        // 出栈结点右孩子存在左子树则入栈右孩子和其左子树中结点
        TreeNode node = root.right;
        while (null != node) {
            sL.push(node);
            node = node.left;
        }
        return root;
    }

    /**
     * 目的：将大于root.val的结点入栈，左孩子的右子树都大于左孩子
     * 出栈结点左孩子存在右子树则入栈左孩子和其右子树中结点
     */
    public TreeNode getRight(Stack<TreeNode> sR) {
        TreeNode root = sR.pop();
        // 出栈结点左孩子存在右子树则入栈右子树中结点
        TreeNode node = root.left;
        while (null != node) {
            sR.push(node);
            node = node.right;
        }
        return root;
    }
}
