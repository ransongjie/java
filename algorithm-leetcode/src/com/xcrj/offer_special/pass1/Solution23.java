package com.xcrj.offer_special.pass1;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 023. 两个链表的第一个重合节点
 * 给定两个单链表的头节点 headA 和 headB ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 */
public class Solution23 {
    /**
     * 散列法
     * 先统计headA于set中，再逐个遍历headB判断set中是否包含某个结点
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> aSet = new HashSet<>();
        ListNode pa = headA;
        while (pa != null) {
            aSet.add(pa);
            pa = pa.next;
        }

        ListNode pb = headB;
        while (pb != null) {
            if (aSet.contains(pb)) {
                return pb;
            }
            pb = pb.next;
        }

        return null;
    }

    /**
     * 双指针，指针交叉，速度相同的双指针要想相遇需要从同样的起点出发
     *
     * 若 a长度等于b，pa pb指针速度一致 不用遍历c就能相遇
     * 若 a长度大于b，pb走完自己的链表(b+c)时，pa也走了长度(b+c)还剩余a-b没走，此时让pb也走a-b。pa和pb都走完a-b后二者就走到了同样的起点往前走
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // 特殊情况处理
        if (null == headA || null == headB) {
            return null;
        }

        // 双指针
        ListNode pa = headA;
        ListNode pb = headB;

        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }

        return pa;
    }

    public static void main(String[] args) {

    }
}
