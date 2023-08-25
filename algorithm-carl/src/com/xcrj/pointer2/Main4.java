package com.xcrj.pointer2;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 * 反转链表
 */
public class Main4 {
    /**
     * 反转链表 从前往后 迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null, tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 反转链表 从前往后 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode cur, ListNode pre) {
        if (cur == null) return pre;
        ListNode tmp = cur.next;
        cur.next = pre;
        return reverse(tmp, cur);
    }

    /**
     * 反转链表 从后往前 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
