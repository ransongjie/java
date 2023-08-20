package com.xcrj.interview150.link;

/**
 * 翻转整个链表
 */
public class Solution03a {
    ListNode reverse(ListNode head) {
        if (head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
