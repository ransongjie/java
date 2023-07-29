package com.xcrj.offer_special.pass3;

import java.util.Stack;

/**
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 */
public class Solution21 {
    /**
     * 双指针同向移动
     * - 构建伪头结点
     * - p指向head，pd指向dummy->head
     * - p先走n步
     * - p和pd同时往前走，直到p走到链表尾结点，pd指向倒数第n+1个节点
     * - 删除倒数第n个结点
     * 
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        //
        ListNode dummy=new ListNode(0,head);
        ListNode pd=dummy;
        ListNode p=head;
        //
        for(int i=0;i<n;i++){
            p=p.next;
        }
        //
        while(p!=null){
            p=p.next;
            pd=pd.next;
        }
        //
        pd.next=pd.next.next;
        return dummy.next;
    }

    /**
     * 栈
     * - 构建虚伪头结点
     * - 全部结点放入栈中
     * - pop n次
     * - 栈顶是倒数第n+1个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        //
        ListNode dummy=new ListNode(0,head);
        ListNode p=dummy;
        //
        Stack<ListNode> s=new Stack<>();
        while(p!=null){
            s.push(p);
            p=p.next;
        }
        //
        for(int i=0;i<n;i++){
            s.pop();
        }
        //xcrj peak() 与 pop()区别
        ListNode pre=s.peek();
        pre.next=pre.next.next;
        return dummy.next;
    }
}
