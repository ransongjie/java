package com.xcrj.basic.pass2.sort;
/**
 * 插入排序链表实现
 */
public class InsertSortL {
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // pre指向已经有序的节点
        ListNode pre = head;
        // cur指向待排序的节点
        ListNode cur = head.next;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        while (cur != null) {
            if (cur.val < pre.val) {
                // 先把cur节点从当前链表中删除，然后再把cur节点插入到合适位置
                pre.next = cur.next;

                // 寻找cur的插入位置，记录前后指针
                ListNode p1 = dummy;
                ListNode p2 = dummy.next;
                while (cur.val > p2.val) {
                    p1 = p2;
                    p2 = p2.next;
                }

                // 插入cur结点
                p1.next = cur;
                cur.next = p2;

                // 继续
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val){
        this.val=val;
    }
}