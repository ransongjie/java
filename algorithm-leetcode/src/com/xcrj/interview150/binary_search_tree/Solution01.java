package com.xcrj.interview150.binary_search_tree;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst
 * 二叉搜索树中第K小的元素
 */
public class Solution01 {

    /**
     * k大根堆 + BFS 广度优先遍历
     * k大根堆，堆顶就是第k小的值
     * - 堆中元素数量<k, 直接入堆
     * - 堆中元素数量>=k, 堆外值更小则入堆
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        //k大根堆，堆顶就是第k小的值
        PriorityQueue<Integer> pque = new PriorityQueue<>((a, b) -> a > b ? -1 : 1);
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            TreeNode node = que.poll();
            // 堆中元素数量<k, 直接入堆
            if (pque.size() < k) {
                pque.offer(node.val);
            }
            // 堆中元素数量>=k, 堆外值更小则入堆
            else if (pque.peek() > node.val) {
                pque.poll();
                pque.offer(node.val);
            }

            if (node.left != null) que.offer(node.left);
            if (node.right != null) que.offer(node.right);
        }

        return pque.peek();
    }

    /**
     * 中序遍历 迭代
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        int cnt = 0;
        while (!s.isEmpty() || p != null) {
            if (p != null) {
                s.push(p);
                p = p.left;
            } else {
                p = s.pop();
//                System.out.println(p.val);
                cnt++;
                if (cnt == k) return p.val;
                p = p.right;
            }
        }
        return 0;
    }
}
