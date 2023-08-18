package com.xcrj.interview150.divide_conquer;

/**
 * https://leetcode.cn/problems/sort-list
 * 排序链表
 * 时间复杂度 O(nlogn)
 */
public class Solution01 {
    /**
     * 自顶向下二路归并
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sort(head, null);
    }

    /**
     * 向下划分直到仅存1个结点或空结点
     *
     * @param head
     * @param tail
     * @return
     */
    private ListNode sort(ListNode head, ListNode tail) {
        if (head == null) return null;
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        // 快慢指针寻找链表中点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        // 继续划分
        ListNode list1 = sort(head, mid);
        ListNode list2 = sort(mid, tail);
        return merge(list1, list2);
    }

    // 归并
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy, p1 = head1, p2 = head2;
        //遍历两链表
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        //未处理完的链表
        if (p1 != null) {
            p.next = p1;
        } else if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }
}
