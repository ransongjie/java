package com.xcrj.offer_special.pass2;
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
        // 记录原链表前一个结点
        ListNode pre=null;
        // 记录原链表当前结点
        ListNode current=head;
        // 记录原链表下一个结点
        ListNode next=null;
        while(null!=current){
            // xcrj 先记录下一次操作
            next=current.next;
            // 反转指针
            current.next=pre;
            // pre移动到current
            pre=current;
            // xcrj 准备进行下一次操作
            current=next;
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
        // 特殊情况
        if(null==head){
            return head;
        }
        // 返回最后1个结点
        if(null==head.next){
            return head;
        }
        // 开始，newHead是最后1个结点，返回最后1个结点
        ListNode newHead=reverseList2(head.next);
        // 开始，head是倒数第2个结点，反转最后两个结点
        head.next.next=head;
        head.next=null;
        return newHead;
    }
}
