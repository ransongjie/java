package com.xcrj.offer_special.pass1;

import java.util.Stack;

/**
 * 剑指 Offer II 025. 链表中的两数相加
 * 给定两个 非空链表 l1和 l2来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 */
public class Solution25 {

    /**
     * 将加数链表反转
     * 头插法构建结果链表
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;

        ListNode head1 = reverse(l1);
        ListNode head2 = reverse(l2);

        ListNode p1 = head1;
        ListNode p2 = head2;
        ListNode headR = null;
        // 进位
        int more = 0;
        // 最高位进位
        int more1 = 0;
        while (p1 != null && p2 != null) {
            int r = p1.val + p2.val + more;
            // 重置
            more = 0;
            if (r > 9) {
                // 十位
                more = r / 10;
                // 个位
                r = r % 10;

                if (p1.next == null || p2.next == null) more1 = more;
            }
            ListNode nodeR = new ListNode(r, headR);
            headR = nodeR;

            p1 = p1.next;
            p2 = p2.next;
        }

        // p1 p2链表长度一致，处理最高位进位
        if (p1 == null && p2 == null && more1 != 0) {
            ListNode nodeR = new ListNode(more1, headR);
            headR = nodeR;
            return headR;
        }

        // p1长度更长
        if (p1 != null) {
            // 最高位进位
            int more2 = 0;
            while (p1 != null) {
                int r = p1.val + more1;
                // 重置
                more1 = 0;
                if (r > 9) {
                    // 十位
                    more1 = r / 10;
                    // 个位
                    r = r % 10;

                    if (p1.next == null) more2 = more1;
                }
                ListNode nodeR = new ListNode(r, headR);
                headR = nodeR;

                p1 = p1.next;
            }
            //处理最高位进位
            if (p1 == null && more2 != 0) {
                ListNode nodeR = new ListNode(more2, headR);
                headR = nodeR;
                return headR;
            }
        }

        // p2长度更长
        if (p2 != null) {
            // 最高位进位
            int more2 = 0;
            while (p2 != null) {
                int r = p2.val + more1;
                // 重置
                more1 = 0;
                if (r > 9) {
                    // 十位
                    more1 = r / 10;
                    // 个位
                    r = r % 10;

                    if (p2.next == null) more2 = more1;
                }
                ListNode nodeR = new ListNode(r, headR);
                headR = nodeR;

                p2 = p2.next;
            }
            //处理最高位进位
            if (p2 == null && more2 != 0) {
                ListNode nodeR = new ListNode(more2, headR);
                headR = nodeR;
                return headR;
            }
        }

        return headR;
    }

    /**
     * 将加数链表反转
     * 头插法构建结果链表
     * addTwoNumbers2 优化 addTwoNumbers1
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;

        ListNode p1 = reverse(l1);
        ListNode p2 = reverse(l2);

        ListNode headR = null;
        int more = 0;
        while (p1 != null || p2 != null || more != 0) {
            int a = p1 == null ? 0 : p1.val;
            int b = p2 == null ? 0 : p2.val;
            int r = a + b + more;
            more = r / 10;
            r %= 10;
            ListNode p = new ListNode(r, headR);
            headR = p;
            p1 = p1 == null ? null : p1.next;
            p2 = p2 == null ? null : p2.next;
        }

        return headR;
    }

    /**
     * 递归反转
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // newhead 尾结点做反转后的头结点
        ListNode newHead = reverse(head.next);
        // head 上1个结点
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    /**
     * 使用栈后进先出的特点反转加法链表
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode headR = null;
        int more = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || more != 0) {
            int a = s1.isEmpty() ? 0 : s1.pop();
            int b = s2.isEmpty() ? 0 : s2.pop();
            int r = a + b + more;
            more = r / 10;
            r %= 10;
            ListNode p = new ListNode(r, headR);
            headR = p;

        }
        return headR;
    }

}
