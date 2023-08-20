package com.xcrj.interview150.link;

/**
 * 反转链表 II
 * https://leetcode.cn/problems/reverse-linked-list-ii
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 */
public class Solution03 {
    ListNode successor = null;

    ListNode reverseBetween(ListNode head, int m, int n) {
        // 从第m个结点开始反转n个结点
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    ListNode reverseN(ListNode head, int n) {
        // successor 记录第 n + 1 个节点
        if (n == 1) {
            successor = head.next;
            // 第n个结点作头结点
            return head;
        }

        // head.next为起点，需要反转前 n - 1 个节点
        ListNode newHead = reverseN(head.next, n - 1);
        // 反转链表
        head.next.next = head;
        // 让反转之后的 head 节点（第n个结点）和后面的节点连起来
        head.next = successor;//结点倒序每次都会赋值直到第m个结点
        return newHead;
    }
}
