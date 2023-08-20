package com.xcrj.interview150.link;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/add-two-numbers
 * 两数相加
 * 数字最高位位于链表结束位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class Solution01 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), p = dummy;
        int more = 0;
        while (l1 != null || l2 != null || more != 0) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int r = a + b + more;
            more = r / 10;
            r %= 10;//当前结点值
            //尾插法
            p.next = new ListNode(r);
            p = p.next;
            // 继续
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        return dummy.next;
    }
}
