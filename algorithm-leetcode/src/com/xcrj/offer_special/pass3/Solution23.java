package com.xcrj.offer_special.pass3;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 023. 两个链表的第一个重合节点
 * 给定两个单链表的头节点 headA 和 headB ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 */
public class Solution23 {
    /**
     * 散列表
     * - headA链表中所有结点放入散列表中
     * - headB链表中每个结点是否在散列表中
     * 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        //
        if (headA == null)
            return null;
        if (headB == null)
            return null;
        //
        Set<ListNode> set = new HashSet<>();
        ListNode ap = headA;
        ListNode bp = headB;
        while (ap != null) {
            set.add(ap);
            ap = ap.next;
        }
        //
        while (bp != null) {
            if (set.contains(bp))
                return bp;
            bp = bp.next;
        }
        return null;
    }

    /**
     * 同速双指针，交叉遍历
     * - 先遍历自己，再遍历他人，相遇即找到 两个链表的第一个重合节点
     * 
     * A链表：a+c
     * B链表：b+c
     * 链表长度一致
     * - A链表：走a
     * - B链表：走b
     * 链表长度不一致
     * - A链表：走a+c+b
     * - B链表：走b+c+a
     * 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //
        if (headA == null)
            return null;
        if (headB == null)
            return null;
        //
        ListNode ap=headA;
        ListNode bp=headB;
        // ap!=bp则一直循环
        while(ap!=bp){
            ap=ap==null?headB:ap.next;
            bp=bp==null?headA:bp.next;
        }

        // xcrj ap=bp返回ap
        return ap;
    }
}
