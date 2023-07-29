package com.xcrj.offer_special.pass4;

/**
 * 链表中的两数相加
 * 链表中的两数相加，构建结果链表
 */
public class Solution025 {
    /**
     * 反转链表
     * 求余求进位
     * 头插法构建结果链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = reverse(l1);
        ListNode n2 = reverse(l2);
        //q进位,r余数
        int q = 0, r = 0, a = 0, b = 0;
        ListNode ans = null, p = null;
        while (n1 != null || n2 != null || q != 0) {
            a = n1 == null ? 0 : n1.val;
            b = n2 == null ? 0 : n2.val;
            r = a + b + q;
            q = r / 10;
            r = r % 10;
            //头插法 r余数做当前结点的值，商左下一个结点的值
            p = new ListNode(r, ans);
            ans = p;
            //
            n1 = n1 == null ? null : n1.next;
            n2 = n2 == null ? null : n2.next;
        }
        return ans;
    }

    /**
     * 反转链表
     */
    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newHead = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }
}
