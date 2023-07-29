package com.xcrj.offer_special.pass3;
/***
 * 剑指 Offer II 024. 反转链表
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 */
public class Solution24 {
    /**
     * pre current next三指针
     * - 通过改变结点的next值=pre，来反转指针
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre=null;
        ListNode cur=head;
        ListNode next=null;
        // xcrj 从当前节点开始反转
        while(cur!=null){
            // 记录下一个结点
            next=cur.next;
            // 反转链表
            cur.next=pre;
            pre=cur;
            // 走到下一个结点
            cur=next;
        }

        return pre;
    }

    /**
     * 递归三指针
     * - pre 
     * - current
     * - next  
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if(head==null)return null;
        if(head.next==null)return head;
        // xcrj 一直递归到最后一个结点，从最后一个结点开始反转
        ListNode newHead=reverseList2(head.next);
        // 后一个结点指向前一个结点
        head.next.next=head;
        head.next=null;
        return newHead;
    }
}
