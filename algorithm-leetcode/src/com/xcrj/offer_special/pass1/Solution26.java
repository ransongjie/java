package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 剑指 Offer II 026. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln-1 → Ln
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 */
public class Solution26 {
    /**
     * 线性表 可以随机访问
     */
    public void reorderList1(ListNode head) {
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        ListNode p = head;
        while (p != null) {
            list.add(p);
            p = p.next;
        }

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            list.get(j).next = null;
            i++;
            if (i == j) return;
            list.get(j).next = list.get(i);
            list.get(i).next = null;
            j--;
        }
    }

    /**
     * 寻找链表中点：奇数链表长度，返回中间；偶数链表长度，返回中间第2个点
     * 反转链表：反转中点之后的链表
     * 合并链表
     */
    public void reorderList2(ListNode head) {
        ListNode mid = middleNode2(head);
        // 奇偶链表长度，都是以mid作为终点
        ListNode l2 = mid.next;
        mid.next = null;

        l2 = reverseList2(l2);
        mergeList2(head, l2);
    }

    /**
     * 中间结点
     * 使用快慢指针寻找中间结点
     * fast的速度是slow的2倍，fast到链表末尾时，slow在链表中中间
     * 奇数链表长度，返回中间
     * 偶数链表长度，返回中间第2个点
     */
    public ListNode middleNode2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 链表结点为奇数时，fast一次走两个结点，最后走到最后1个结点
        // 链表结点为偶数时，fast一次走两个结点，最后走到倒数第2个结点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 递归
     * 本质利用栈先进后出的特性
     */
    public ListNode reverseList2(ListNode head) {
        // 递归出口 倒数第二个结点就开始出
        if (head == null || head.next == null) {
            return head;
        }
        // newhead 尾结点做反转后的头结点
        ListNode newHead = reverseList2(head.next);
        // 下1个结点指向前1个结点
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public void mergeList2(ListNode l1, ListNode l2) {
        ListNode l1Temp;
        ListNode l2Temp;
        while (l1 != null && l2 != null) {
            // ！！变换结点的链接之前，先保存之前的链接
            l1Temp = l1.next;
            l2Temp = l2.next;

            l1.next = l2;
            // l1=l1.next不行因此l1.next等于l2
            l1 = l1Temp;
            l2.next = l1;
            l2 = l2Temp;
        }
    }

    /**
     * 栈
     * n个结点的链表会连接n-1次
     * 变化结点的连接方式之前先保存之前的连接方式
     */
    public void reorderList3(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode h = head;
        // 统计链表结点个数
        int count = 0;
        while (h != null) {
            stack.push(h);
            h = h.next;
            count++;
        }
        // 特殊情况1个结点
        if (count == 1) return;

        ListNode p = head;
        ListNode d = head;
        ListNode s = null;
        // 记录连接次数
        int linkCnt = 0;
        // 保存之前的连接
        ListNode dTmp;
        while (true) {
            // 保存之前的连接方式
            dTmp = d.next;

            s = stack.pop();
            p.next = s;
            p = s;
            linkCnt++;
            // 4个结点连接3次
            if (linkCnt + 1 == count) {
                s.next = null;
                return;
            }

            // 使用之前的连接方式
            d = dTmp;
            p.next = d;
            p = d;
            linkCnt++;
            // 3个结点连接2次
            if (linkCnt + 1 == count) {
                d.next = null;
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] as = new int[]{1, 2, 3};
        ListNode p = new ListNode(as[0]);
        ListNode head = p;
        for (int i = 1; i < as.length; i++) {
            ListNode n = new ListNode(as[i]);
            p.next = n;
            p = n;
        }

        Solution26 solution26 = new Solution26();
        solution26.reorderList3(head);
        ListNode l = head;
        while (l != null) {
            System.out.print(l.val + "-");
            l = l.next;
        }
    }
}
