package com.xcrj.palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/aMhZSa/description/
 * 是否回文链表
 */
public class Main3 {
    //转储到链表中判断
    public boolean isPalindrome(ListNode head) {
        //转储到list
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        //判断回文链表
        int l = 0, r = list.size() - 1;
        while (l < r) {
            if (list.get(l) == list.get(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }

    //可以反转整个链表

    //链表中点，反转链表，判断回文链表，反转回来
    public boolean isPalindrome2(ListNode head) {
        //特征情况
        if (head == null) return false;
        if (head.next == null) return true;
        //快慢指针获取链表中点
        ListNode slow = head, fast = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) break;
        }
        //反转链表
        ListNode newHead = reverse(slow);
        //判断回文链表
        ListNode p = head, q = newHead;
        while (p != null && q != null && p != q) {//p!=q 避免奇偶判断回文链表中点
            if (p.val == q.val) {
                p = p.next;
                q = q.next;
            } else {
                reverse(newHead);//恢复数据
                return false;
            }
        }
        reverse(newHead);//恢复数据
        return true;
    }

    private ListNode reverse(ListNode head) {
        //找到新头结点
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode newHead = reverse(head.next);
        //反转指针
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
