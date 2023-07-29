package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 026. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln-1 → Ln
 * 重排链表为
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 */
public class Solution26 {
    /**
     * 线性表
     * @param head
     */
    public void reorderList1(ListNode head) {
        // 将链表结点转储到list中
        List<ListNode> list=new ArrayList<>();
        ListNode p=head;
        while(null!=p){
            list.add(p);
            p=p.next;
        }
        // 重构链表
        int i=0;
        int j=list.size()-1;
        while(i<j){
            list.get(i).next=list.get(j);
            list.get(j).next=null;
            i++;
            if(i==j){
                return;
            }
            list.get(j).next=list.get(i);
            list.get(i).next=null;
            j--;
        }
    }

    /**
     * 起点到中点链表和终点到中点链表重构
     * - 寻找链表中点
     * - 反转中点后链表
     * @param head
     */
    public void reorderList2(ListNode head) {
        ListNode middleNode=middleNode(head);
        ListNode last=reverseList(middleNode.next);
        middleNode.next=null;
        merge2List(head, last);
    }

    /**
     * 快慢指针寻找链表中点
     */
    public ListNode middleNode(ListNode head){
        ListNode slow=head;
        ListNode fast=head;
        // xcrj fast.next为null后再next报空指针异常
        while(null!=fast.next&&null!=fast.next.next){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /**
     * 递归反转链表
     * @return
     */
    public ListNode reverseList(ListNode head){
        // 特殊情况
        if(null==head){
            return head;
        }
        // 找到尾部结点返回
        if(null==head.next){
            return head;
        }
        // 寻找尾部结点
        ListNode newHead=reverseList(head.next);
        // 递归末尾两结点重新连接
        head.next.next=head;
        head.next=null;
        return newHead;
    }

    /**
     * 合并两个链表
     * @param l1
     * @param l2
     */
    public void merge2List(ListNode l1,ListNode l2){
        // 临时保存下一步才处理的结点
        ListNode l1Temp=null;
        ListNode l2Temp=null;
        while(null!=l1&&null!=l2){
            l1Temp=l1.next;
            l2Temp=l2.next;
            l1.next=l2;
            l1=l1Temp;
            l2.next=l1;
            l2=l2Temp;
        }
    }
}
