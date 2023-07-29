package com.xcrj.offer_special.pass2;

import java.util.Stack;

/**
 * 剑指 Offer II 025. 链表中的两数相加
 * 给定两个 非空链表 l1和 l2来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 */
public class Solution25 {
    /**
     * 递归反转链表
     * 反向尾插法构建结果链表
     * - 两数相加可能有进位
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // 特殊情况
        if(null==l1||null==l2){
            return null;
        }
        // 反转链表
        ListNode p1=reverse(l1);
        ListNode p2=reverse(l2);
        // 反向头插法构建结果链表
        ListNode head=null;
        // 进位
        int quotient=0;
        int remainder=0;
        // p1链表还存在结点||p2链表还存在结点||有进位 都需要构建新结点
        while(null!=p1||null!=p2||0!=quotient){
            int a=null==p1?0:p1.val;
            int b=null==p2?0:p2.val;
            // 和
            int r=a+b+quotient;
            // 商
            quotient=r/10;
            // 余数
            remainder=r%10;
            // 构建新结点
            ListNode p=new ListNode(remainder,head);
            // 新结点做头结点
            head=p;
            // 处理下一个结点
            p1=null==p1?null:p1.next;
            p2=null==p2?null:p2.next;
        }
        return head;
    }

    /**
     * 参考 Solution24
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        // 特殊情况
        if(null==head){
            return head;
        }
        // 返回最后1个结点
        if(null==head.next){
            return head;
        }
        // 将原链表最后1个结点做头结点
        ListNode newHead=reverse(head.next);
        // 从倒数第2个结点开始反转
        head.next.next=head;
        head.next=null;
        return newHead;
    }

    /**
     * 栈反转链表
     * 反向尾插法构建结果链表
     * - 两数相加可能有进位
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 特殊情况
        if(null==l1||null==l2){
            return null;
        }
        // 使用栈反转链表
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        while(null!=l1){
            stack1.push(l1.val);
            l1=l1.next;
        }
        while(null!=l2){
            stack2.push(l2.val);
            l2=l2.next;
        }
        // 反向尾插法构建结果链表
        ListNode head=null;
        int quotient=0;
        int remainder=0;
        while(!stack1.isEmpty()||!stack2.isEmpty()||0!=quotient){
            int a=stack1.isEmpty()?0:stack1.pop();
            int b=stack2.isEmpty()?0:stack2.pop();
            int r=a+b+quotient;
            quotient=r/10;
            remainder=r%10;
            ListNode p=new ListNode(remainder,head);
            head=p;
        }
        return head;
    }
}
