package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指 Offer II 027. 回文链表
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 */
public class Solution27 {

    /**
     * 栈反转链表
     */
    public boolean isPalindrome1(ListNode head) {
        // 存储到栈中
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        // 记录链表结点数量
        int cnt = 0;
        while (p != null) {
            stack.push(p.val);
            cnt++;
            p = p.next;
        }

        // 判断回文数
        // 判断到链表的中点之后的判断都是重复判断
        p = head;
        int s = stack.pop();
        // 计数走到1半，奇数个结点 half==cnt/2 即可，偶数个结点 half==cnt/2即可
        int half = 0;
        while (p != null && !stack.isEmpty() && half != cnt / 2) {
            half++;
            if (p.val == s) {
                p = p.next;
                s = stack.pop();
            } else return false;
        }

        return true;
    }

    /**
     * 先复制到链表，再用双指针
     */
    public boolean isPalindrome2(ListNode head) {
        // 转储到链表中
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }

        // 双指针判断
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (list.get(i).equals(list.get(j))) {
                i++;
                j--;
            } else return false;
        }

        return true;
    }

    // 记录左指针
    private ListNode pi;

    /**
     * 递归 双指针 pi pj
     * 1. 先让pj走到最后1个结点
     * 2. 再判断回文数pi.val==pj.val
     */
    public boolean isPalindrome3(ListNode head) {
        // 开始pi和pj都指向第1个结点
        pi = head;
        ListNode pj = head;
        return recursivelyCheck3(pj);
    }

    private boolean recursivelyCheck3(ListNode pj) {
        // 先让pj走到最后1个结点，以继续递归的方式走到最后1个结点
        if (pj != null) {
            // false pi.val不等于pj.val 则不是回文数列
            if (!recursivelyCheck3(pj.next)) {
                return false;
            }
            // 再判断回文数
            if (pi.val == pj.val) {
                // pi往右走
                pi = pi.next;
            } else return false;
        }
        // pj以递归回退的方式往左走
        return true;
    }

    /**
     * 找到链表中点
     * 反转中点之后的链表
     * 判断回文数
     * 恢复反转的链表
     */
    public boolean isPalindrome4(ListNode head) {
        ListNode mid = middleNode4(head);
        ListNode head2 = reverseList4(mid.next);
        boolean flag = palindrome4(head, head2);
        ListNode midNext = reverseList4(head2);
        mid.next = midNext;
        return flag;
    }

    /**
     * 中间结点
     * 使用快慢指针寻找中间结点
     * fast的速度是slow的2倍，fast到链表末尾时，slow在链表中中间
     * 奇数链表长度，返回中间
     * 偶数链表长度，返回中间第2个点
     */
    public ListNode middleNode4(ListNode head) {
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
    public ListNode reverseList4(ListNode head) {
        // 递归出口 倒数第二个结点就开始出
        if (head == null || head.next == null) {
            return head;
        }
        // newhead 尾结点做反转后的头结点
        ListNode newHead = reverseList4(head.next);
        // 下1个结点指向前1个结点
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public boolean palindrome4(ListNode head, ListNode head2) {
        ListNode p1 = head;
        ListNode p2 = head2;
        while (p1 != null && p2 != null) {
            if (p1.val == p2.val) {
                p1 = p1.next;
                p2 = p2.next;
            } else return false;
        }

        return true;
    }
}
