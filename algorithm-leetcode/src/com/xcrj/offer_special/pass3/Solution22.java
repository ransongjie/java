package com.xcrj.offer_special.pass3;

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
        //特殊情况
        if(head==null){
            return null;
        }
        //
        Set<ListNode> set=new HashSet<>();
        ListNode p=head;
        while(p!=null){
            if(set.contains(p)){
                return p;
            }
            set.add(p);
            p=p.next;
        }

        return null;
    }

    /**
     * 快慢指针
     * - fast指针是slow指针的2倍速
     * - slow指针和fast指针相遇
     * - a是head到环开始结点的距离
     * - b是环开始结点到相遇结点的距离
     * - c是fast指针从相遇结点再走到相遇结点多走的距离
     * - 2(a+b)=(a+b+c+b)》a=c
     * - fast指针和slow指针相遇之后，p指针从头结点出发到与slow指针相遇的结点即环的入口结点
     */
    public ListNode detectCycle2(ListNode head) {
        //
        if(head==null) return null;
        //
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null){
            // xcrj fast.next.next可以等于null
            if(fast.next!=null){
                //2倍速
                fast=fast.next.next;
            }
            // 非有环链表
            else{
                return null;
            }
            slow=slow.next;
            //
            if(fast==slow){
                ListNode p=head;
                // xcrj 未相遇之前同速往下走
                while(p!=slow){
                    p=p.next;
                    slow=slow.next;
                }
                return p;
            }
        }
        //
        return null;
    }
}
