package com.xcrj.offer_special.pass2;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer II 078. 合并排序链表
 * 给定一个链表数组，每个链表都已经按升序排列。
 * 请将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Solution78 {
    /**
     * 新链表与已经合并的链表进行合并
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode r=null;
        for(int i=0;i<lists.length;i++) r=merge(r, lists[i]);
        return r;
    }

    /**
     * 合并两个链表，二路归并中的merge
     */
    private ListNode merge(ListNode a, ListNode b) {
        // 先处理特殊情况
        if(a==null&&b==null)return null;
        if(a!=null&&b==null)return a;
        if(a==null&&b!=null)return b;

        // 合并链表，尾插法
        ListNode dummy=new ListNode();
        ListNode p1=a,p2=b,p=dummy;
        
        // 两个链表中都有结点
        // 下面两个if，只会执行其中1个
        while(p1!=null&&p2!=null){
            if(p1.val<p2.val){
                p.next=p1;
                p1=p1.next;
            }else{
                p.next=p2;
                p2=p2.next;
            }
            p=p.next;
        }

        // a链中还有结点，b链中没有结点
        if(p1!=null)p.next=p1;
        // b链中还有结点，a链中没有结点
        if(p2!=null)p.next=p2;

        return dummy.next;
    }

    /**
     * 类似自顶向下二路归并（递归二路归并）
     * - 只需要控制子序列长度
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeR(lists, 0, lists.length-1);
    }

    /**
     * 递归合并
     */
    private ListNode mergeR(ListNode[] lists, int l, int r) {
        if(l==r) return lists[l];
        if(l>r) return null;

        int mid=(l+r)>>1;
        ListNode head1=mergeR(lists, l, mid);
        ListNode head2=mergeR(lists, mid+1, r);
        return merge(head1,head2);
    }

    /**
     * 使用优先队列
     * - offer(每个链表的结点)
     * - poll(每个链表的结点的最小值结点)
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        //  先把每个链表的头结点（链表对头是最小值）放入优先队列
        for(ListNode p:lists){
            if(p!=null)pque.offer(new CmpableNode(p.val, p));
        }

        // 构建结果链表，使用尾插法
        ListNode dummy=new ListNode();
        ListNode p=dummy;

        // 出队优先队列头结点（最小值），入队出队结点的下1个结点
        while(!pque.isEmpty()){
            CmpableNode cNode=pque.poll();
            p.next=cNode.nod;
            p=p.next;
            if(p.next!=null) pque.offer(new CmpableNode(p.next.val, p.next));
        }

        return dummy.next;
    }

    Queue<CmpableNode> pque=new PriorityQueue<>();

    /**
     * 优先队列必须要是可排序的
     */
    class CmpableNode implements Comparable<CmpableNode> {
        int val;
        ListNode nod;

        CmpableNode(int value,ListNode node){
            val=value;
            nod=node;
        }


        @Override
        public int compareTo(CmpableNode o) {
            return val-o.val;
        }
    }
}
