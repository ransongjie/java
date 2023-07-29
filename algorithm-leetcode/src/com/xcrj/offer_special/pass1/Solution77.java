package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 077. 链表排序
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class Solution77 {
    /**
     * 数组作存储结构
     * 二路归并排序：
     * 1. 子序列长度从1到n/2(把数组划分为2个子序列)
     * 2. 从左往右一次比较2个子序列
     * <p>
     * <p>
     * 非递归实现
     * 1. 子序列长度，h从1开始到h/2，作2倍变化2h
     * 2. 子序列个数，根据剩余子序列的个数执行相应的操作
     * 3. 记录个数，根据子序列中记录个数执行相应的操作
     * <p>
     * 递归实现：使用递归控制子序列的长度和个数
     * 1. 记录个数，根据子序列中记录个数执行相应的操作
     */

    /**
     * 自顶向下二路归并-递归二路归并
     * - 子序列的长度和个数：递归控制
     * - 记录个数：根据子序列中记录个数执行相应的操作
     */
    public ListNode sortList1(ListNode head) {
        return way2MergeSortR(head, null);
    }

    /**
     * @param head 真实头结点
     * @param tail 尾结点 ！初始等于null
     */
    private ListNode way2MergeSortR(ListNode head, ListNode tail) {
        // 无结点
        if (head == null) return null;

        // 有2个结点取前1个结点
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        // 使用快慢指针确定链表中点
        ListNode fast = head, slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;

        // 二路归并递归左子序列
        ListNode head1 = way2MergeSortR(head, mid);
        // 二路归并递归右子序列
        ListNode head2 = way2MergeSortR(mid, tail);
        // 二路归并
        return merge(head1, head2);
    }

    /**
     * 记录个数：根据子序列中记录个数执行相应的操作
     *
     * @param head1 链表1
     * @param head2 链表2
     */
    private ListNode merge(ListNode head1, ListNode head2) {
        // 使用伪头结点统一头结点和普通结点的操作
        ListNode dummyHead = new ListNode();
        ListNode p1 = head1, p2 = head2, p = dummyHead;
        // 两个链表中都有记录
        while (null != p1 && null != p2) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        // 下面两个if语句只可能执行其中1个
        // 链表1中还有记录
        if (null != p1) {
            p.next = p1;
        }
        // 链表2中还有记录
        if (null != p2) {
            p.next = p2;
        }
        // 返回真实头结点
        return dummyHead.next;
    }

    /**
     * 自底向上二路归并-非递归二路归并
     * 1. 子序列长度，h从1开始作2倍变化2h
     * 2. 子序列个数，根据剩余子序列的个数执行相应的操作，
     * 3. 记录个数，根据子序列中记录个数执行相应的操作
     */
    public ListNode sortList2(ListNode head) {
        // 没有结点
        if (null == head) return null;

        // 统计链表长度
        int len = 0;
        ListNode p = head;
        while (null != p) {
            len++;
            p = p.next;
        }

        // 伪头结点统一头结点和普通结点操作
        ListNode dummyHead = new ListNode(0, head);

        // 子序列长度，h从1开始作2倍变化2h。subLen <<= 1，乘2
        for (int subLen = 1; subLen < len; subLen <<= 1) {
            // 已归并子链表尾结点。初始时，没有任何被归并的子链表
            ListNode mergedTail = dummyHead;

            // 获取子序列，最后一个子序列的长度可以小于“子序列长度”
            ListNode cur = dummyHead.next;
            while (null != cur) {
                // 获取待比较前一个子链表
                ListNode head1 = cur;
                for (int i = 1; i < subLen&&null!=cur.next; i++) {
                    cur = cur.next;
                }

                // 获取待比较后一个子链表
                ListNode head2 = cur.next;
                /// 封闭待比较前一个子序列
                cur.next = null;
                cur = head2;
                for (int i = 1; i < subLen&&null!=cur&&null!=cur.next; i++) {
                    cur = cur.next;
                }

                // 记录下一次二路归并的起始结点
                ListNode next2WayStart = null;
                if (null != cur) {
                    next2WayStart = cur.next;
                    /// 封闭待比较后一个子序列
                    cur.next = null;
                }

                // 开始二路归并
                ListNode mergedHead = merge(head1, head2);

                // 连接已归并子链表
                mergedTail.next = mergedHead;
                // 到达已归并子链表的尾部
                while (null != mergedTail.next) mergedTail = mergedTail.next;

                // 开始下一次二路归并
                cur = next2WayStart;
            }

        }
        return dummyHead.next;
    }

}
