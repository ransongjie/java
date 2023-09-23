package com.xcrj.basic.pass2.sort;

/**
 * 归并排序链表实现
 */
public class MergeSortL {
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // 获取链表中间节点
        ListNode mid = getMid(head);

        // 链表拆分为head和second两个子链表
        ListNode second = mid.next;
        mid.next = null;

        // 递归子链表
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(second);

        return merge(right, left);
    }

    // 返回链表之间节点，快慢指针
    private ListNode getMid(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode faster = head.next;
        while (faster != null && faster.next != null) {
            slow = slow.next;
            faster = faster.next.next;
        }
        return slow;
    }

    // 两个有序链表的归并
    private ListNode merge(ListNode l1, ListNode l2) {
        // 辅助节点，排好序的节点将会链接到dummy后面
        ListNode dummy = new ListNode(0);
        // tail指向最后一个排好序的节点，尾插法
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            // 移动tail指针
            tail = tail.next;
        }

        // l1或l2中的剩余结点
        if (l1 != null)
            tail.next = l1;
        else
            tail.next = l2;

        return dummy.next;
    }
}
