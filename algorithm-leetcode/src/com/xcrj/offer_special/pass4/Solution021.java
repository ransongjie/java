package com.xcrj.offer_special.pass4;

/**
 * 删除链表的倒数第 n 个结点
 */
public class Solution021 {
    /**
     * i指针先走n步，ij指针再同时往后走
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode pi = dummy, pj = head;//pi倒数第n+1个结点
        for (int i = 0; i < n; i++) {
            pj = pj.next;
        }
        while (pj != null) {
            pi = pi.next;
            pj = pj.next;
        }
        pi.next = pi.next.next;
        return dummy.next;
    }
}
