package com.xcrj.offer_special.pass2;

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
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> set=new HashSet<>();
        ListNode pa=headA;
        while(null!=pa){
            set.add(pa);
            pa=pa.next;
        }

        ListNode pb=headB;
        while(null!=pb){
            if(set.contains(pb)){
                return pb;
            }
            pb=pb.next;
        }

        return null;
    }

    /**
     * 同速双指针，交叉遍历
     * - 先遍历自己，再遍历他人，相遇即找到 两个链表的第一个重合节点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // 链表为空不可能相交
        if(null==headA||null==headB){
            return null;
        }

        /**
         * A链表：a+c
         * B链表：b+c
         * 链表长度一致
         * - A链表：走a
         * - B链表：走b
         * 链表长度不一致
         * - A链表：走a+c+b
         * - B链表：走b+c+a
         */
        ListNode pA=headA;
        ListNode pB=headB;
        while(pA!=pB){
            pA=null==pA?headB:pA.next;
            pB=null==pB?headA:pB.next;
        }
        return pA;
    }
}
