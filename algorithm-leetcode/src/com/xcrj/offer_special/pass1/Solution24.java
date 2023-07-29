package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 024. 反转链表
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 */
public class Solution24 {

    /**
     * 迭代
     * 存放当前结点 之前结点 和 下一个结点
     */
    public ListNode reverseList1(ListNode head) {
        // 头结点的前1个结点指针为null
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            // 当前结点的下1个结点为之前的结点
            current.next = pre;
            // 之前指针移动到当前指针
            pre = current;
            // 当前指针后移
            current = next;
        }

        return pre;
    }

    /**
     * 递归
     * 本质利用栈先进后出的特性
     */
    public ListNode reverseList2(ListNode head) {
        // 递归出口 倒数第二个结点就开始出
        if (head == null || head.next == null) {
            return head;
        }
        // newhead 尾结点做反转后的头结点
        ListNode newHead = reverseList2(head.next);
        // 下1个结点指向前1个结点
        head.next.next = head;
        head.next = null;

        return newHead;
    }

}
