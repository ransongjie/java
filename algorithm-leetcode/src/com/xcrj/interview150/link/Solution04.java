package com.xcrj.interview150.link;

/**
 * 删除链表的倒数第 N 个结点
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class Solution04 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode pi = dummy, pj = head;//pi倒数第n+1个结点

        // pj先走n步
        for (int i = 0; i < n; i++) {
            pj = pj.next;
        }

        // pi和pj同时往后走，pj到null，pi是倒数第n个
        while (pj != null) {
            pi = pi.next;
            pj = pj.next;
        }
        pi.next = pi.next.next;
        return dummy.next;
    }
}
