package com.xcrj.offer_special.pass1;

import java.util.Stack;

/**
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class Solution21 {


    /**
     * 双指针同向移动，i指向遍历的当前结点，j指向从i倒数第n个结点，当i-j=n时，j移动，最终当i指向最后1个元素是，j指向倒数第n个元素
     * 往头结点之前再插入1个结点（伪头结点），可以不用单独考虑如何处理头结点
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // 构造伪头结点，往链表头之前插入了1个新的结点，可以不会单独处理倒数第n个结点是头结点的情况
        ListNode dummy = new ListNode(0, head);
        ListNode pi = head;
        ListNode pj = dummy;
        // pi先走n步
        for (int i = 0; i < n; ++i) {
            pi = pi.next;
        }
        // pi和pj同时往后走，直到pi到达尾结点，此时pj就是倒数第n个结点
        while (pi != null) {
            pi = pi.next;
            pj = pj.next;
        }
        pj.next = pj.next.next;
        // 伪头结点的下一个结点才是头结点
        return dummy.next;
    }

    /**
     * 栈 利用栈的后进先出
     * 将所有结点入栈，出栈的第n个结点就是倒数第n个结点
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        // 虚伪头结点
        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        // 入栈
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        // 出栈
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        // 因为有虚伪头结点，因此栈顶是 第n-1个结点
        ListNode pre = stack.peek();
        pre.next = pre.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
