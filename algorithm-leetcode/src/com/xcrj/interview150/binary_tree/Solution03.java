package com.xcrj.interview150.binary_tree;

/**
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii
 * 填充每个节点的下一个右侧节点指针 II
 */
public class Solution03 {
    /**
     * 每一层都看成链表
     * 第1层链表只有1个结点
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return root;
        //第一层链表
        Node cur = root;
        while (cur != null) {
            Node nxtDummy = new Node(0);
            Node p = nxtDummy;
            //遍历上层链表，构建下层链表
            while (cur != null) {
                if (cur.left != null) {
                    p.next = cur.left;
                    p = p.next;
                }
                if (cur.right != null) {
                    p.next = cur.right;
                    p = p.next;
                }
                cur = cur.next;
            }
            // 来到下层链表
            cur = nxtDummy.next;
        }
        return root;
    }
}
