package com.xcrj.pointer2;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * 删除链表的倒数第 N 个结点
 */
public class Main5 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // p1=dummy, 倒数第n个结点是p1.next
        ListNode dummy = new ListNode(-1, head), p1 = dummy, p2 = head;
        // 先走n次
        for (int i = 0; i < n; i++) {
            p2 = p2.next;
        }

        // p1和p2一起往后走
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = p1.next.next;// 删除倒数第n个结点

        return dummy.next;
    }
}
