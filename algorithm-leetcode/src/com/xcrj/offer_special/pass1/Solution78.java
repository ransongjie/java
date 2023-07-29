package com.xcrj.offer_special.pass1;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer II 078. 合并排序链表
 * 给定一个链表数组，每个链表都已经按升序排列。
 * 请将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 分析：
 * - 每个链表都已经按升序排列。
 */
public class Solution78 {
    /**
     * 新链表与已经合并的链表进行合并
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode r = null;
        for (int i = 0; i < lists.length; i++) {
            r = merge(r, lists[i]);
        }

        return r;
    }

    /**
     * 合并两个链表
     */
    private ListNode merge(ListNode a, ListNode b) {
        // 链表为空
        if (null == a && null == b) {
            return null;
        }
        if (null == a && null != b) {
            return b;
        }
        if (null != a && null == b) {
            return a;
        }

        // 合并链表，尾插法
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        ListNode pa = a;
        ListNode pb = b;
        while (null != pa && null != pb) {
            if (pa.val <= pb.val) {
                tail.next = pa;
                pa = pa.next;
            } else {
                tail.next = pb;
                pb = pb.next;
            }

            tail = tail.next;
        }

        // 剩余链表
        if (null != pa) tail.next = pa;
        if (null != pb) tail.next = pb;

        return dummyHead.next;
    }

    /**
     * 类似自顶向下二路归并（递归二路归并）
     * - 只需要控制子序列长度
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeR(lists, 0, lists.length - 1);
    }

    /**
     * 递归合并
     */
    private ListNode mergeR(ListNode[] lists, int l, int r) {
        // 递归出口
        if (l == r) return lists[l];
        // 特殊情况
        if (l > r) return null;

        // 二分
        int mid = (l + r) >> 1;
        // 递归左侧
        ListNode head1 = mergeR(lists, l, mid);
        // 递归右侧
        ListNode head2 = mergeR(lists, mid + 1, r);
        // 合并两序列
        return merge(head1, head2);
    }

    /**
     * 使用优先队列
     * - offer(每个链表的结点)
     * - poll(每个链表的结点的最小值结点)
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        //  先把每个链表的头结点（比较所有最小值）放入优先队列
        for (ListNode p : lists) {
            if (null != p) {
                this.pQueue.offer(new CmpableNode(p.val, p));
            }
        }

        // 构建结果链表，使用尾插法
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        // 出队优先队列头结点（最小值），入队出队结点的下1个结点
        while (!this.pQueue.isEmpty()) {
            CmpableNode cmpableNode = this.pQueue.poll();
            ListNode p = cmpableNode.p;
            tail.next = p;
            tail = tail.next;
            if (null != p.next) this.pQueue.offer(new CmpableNode(p.next.val, p.next));
        }
        return dummyHead.next;
    }

    /**
     * 优先队列必须要是可排序的
     */
    class CmpableNode implements Comparable<CmpableNode> {
        int val;
        ListNode p;

        public CmpableNode(int val, ListNode p) {
            this.val = val;
            this.p = p;
        }

        @Override
        public int compareTo(CmpableNode o) {
            return this.val - o.val;
        }
    }

    // 优先队列
    Queue<CmpableNode> pQueue = new PriorityQueue<>();
}
