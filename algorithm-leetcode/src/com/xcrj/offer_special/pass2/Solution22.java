package com.xcrj.offer_special.pass2;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 022. 链表中环的入口节点
 */
public class Solution22 {
    /**
     * 有环 问题转换 访问了以前被访问过的结点
     * - 使用hash表记录之前访问过的结点
     * 
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        if (null == head) {
            return head;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (null != p) {
            if (set.contains(p)) {
                return p;
            }
            set.add(p);
            p = p.next;
        }
        return null;
    }

    /**
     * 快慢指针
     * - fast指针是slow指针的2倍速
     * slow指针和fast指针相遇
     * - a是head到环开始结点的距离
     * - b是环开始结点到相遇结点的距离
     * - c是fast指针从相遇结点再走到相遇结点多走的距离
     * - 2(a+b)=(a+b+c+b)》a=c
     * - fast指针和slow指针相遇之后，p指针从头结点出发到与slow指针相遇的结点即环的入口结点
     * 
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (null == head) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        // 移动快慢指针直到相遇
        while (null != fast) {
            // xcrj 快慢指针操作标准流程
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            slow = slow.next;

            // 快慢指针相遇之后，p指针从头结点出发，slow指针继续移动，直到相遇
            if (slow == fast) {
                ListNode p = head;
                // xcrj 两种情况时，循环非结果，才能得到结果
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }
}
