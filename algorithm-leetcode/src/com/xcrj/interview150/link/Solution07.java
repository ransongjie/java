package com.xcrj.interview150.link;

/**
 * https://leetcode.cn/problems/partition-list
 * 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class Solution07 {
    public ListNode partition(ListNode head, int x) {
        // l链表存储所有<x的结点
        ListNode l = new ListNode(0);
        // ge链表存储所有>=x的结点
        ListNode ge = new ListNode(0);
        ListNode leDummy = l;
        ListNode gDummy = ge;

        while (head != null) {
            if (head.val < x) {
                l.next = head;
                l = l.next;
            } else {
                ge.next = head;
                ge = ge.next;
            }
            head = head.next;
        }
        ge.next = null;

        //连接 le链表和g链表
        l.next = gDummy.next;

        return leDummy.next;
    }
}
