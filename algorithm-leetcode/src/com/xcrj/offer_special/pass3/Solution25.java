package com.xcrj.offer_special.pass3;

import java.util.Stack;

/**
 * 剑指 Offer II 025. 链表中的两数相加
 * 给定两个 非空链表 l1和 l2来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 */
public class Solution25 {
    /**
     * 反转链表
     * 头插法构建结果链表
     * 两数相加可能有进位
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode p1=reverse(l1);
        ListNode p2=reverse(l2);
        //
        int quotient=0;
        int remainder=0;
        ListNode head=null;
        // p1, p2, quotient有值都需要继续
        while(p1!=null||p2!=null||quotient!=0){
            // 新结点
            int p1val=p1==null?0:p1.val;
            int p2val=p2==null?0:p2.val;
            int c=p1val+p2val+quotient;
            remainder=c%10;
            quotient=c/10;
            ListNode p=new ListNode(remainder,head);
            head=p;
            //
            p1=p1==null?null:p1.next;
            p2=p2==null?null:p2.next;
        }
        return head;
    }

    private ListNode reverse(ListNode head){
        if(head==null) return null;
        if(head.next==null) return head;
        //
        ListNode newHead=reverse(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }

    /**
     * 栈存值
     * 头插法构建结果链表
     * 两数相加可能有进位
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head=null;
        //
        Stack<Integer> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();
        while(l1!=null){
            s1.push(l1.val);
            l1=l1.next;
        }
        while(l2!=null){
            s2.push(l2.val);
            l2=l2.next;
        }
        //
        int quotient=0;
        int remainder=0;
        while(!s1.isEmpty()||!s2.isEmpty()||quotient!=0){
            int s1val=s1.isEmpty()?0:s1.pop();
            int s2val=s2.isEmpty()?0:s2.pop();
            int c=s1val+s2val+quotient;
            remainder=c%2;
            quotient=c/2;
            ListNode p=new ListNode(remainder,head);
            head=p;
        }

        //
        return head;
    }
}
