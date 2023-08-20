package com.xcrj.interview150.link;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
 * 删除排序链表中的重复元素 II
 * <p>
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 */
public class Solution05 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        while (p.next != null && p.next.next != null) {
            // 下一个结点和下下一个结点 判断相等
            if (p.next.val == p.next.next.val) {
                int x = p.next.val;
                // 去掉重复结点 下一个结点 = 下下一个结点
                while (p.next != null && p.next.val == x) {
                    p.next = p.next.next;
                }
            } else {
                p = p.next;
            }
        }

        return dummy.next;
    }
}
