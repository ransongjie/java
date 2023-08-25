package com.xcrj.pointer2;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci
 * 链表相交
 * 链表无环
 */
public class Main6 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 求长度
        int la = 0, lb = 0;
        ListNode pa = headA, pb = headB;
        while (pa != null) {
            la++;
            pa = pa.next;
        }
        while (pb != null) {
            lb++;
            pb = pb.next;
        }

        // 确定长链表，短链表
        int diff;
        ListNode pg, pl;
        if (la >= lb) {
            pg = headA;
            pl = headB;
            diff = la - lb;
        } else {
            pg = headB;
            pl = headA;
            diff = lb - la;
        }

        // 长的链表先走diff，长短链表一起走，判断指针是否相等
        for (int i = 0; i < diff; i++) {
            pg = pg.next;
        }
        while (pg != null && pg != pl) {
            pg = pg.next;
            pl = pl.next;
        }
        return pg;
    }
}
