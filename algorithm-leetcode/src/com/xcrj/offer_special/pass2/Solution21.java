package com.xcrj.offer_special.pass2;

import java.util.Stack;

/**
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 */
public class Solution21 {
    /**
     * 双指针同向移动
     * - 构建伪头结点
     * - pi指向head，pj指向dummy->head
     * - pi先走n步
     * - pj和pi同时往前走，直到pi到链表尾结点
     * 
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode pj = dummy;
        ListNode pi = head;
        // pi先走n步
        for (int i = 0; i < n; i++) {
            pi = pi.next;
        }
        // pj和pi同时往前走，直到pi到链表尾结点
        while (pi != null) {
            pi = pi.next;
            pj = pj.next;
        }
        // 剔除倒数第n个结点，重新连接链表
        pj.next = pj.next.next;
        return dummy.next;
    }

    /**
     * 利用栈的先进后出
     * 
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        for (int i = 0; i < n; i++) {
            // xcrj pop()
            stack.pop();
        }
        // xcrj peek()
        ListNode pre = stack.peek();
        pre.next = pre.next.next;
        return dummy.next;
    }
}
