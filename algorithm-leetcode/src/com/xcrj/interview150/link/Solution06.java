package com.xcrj.interview150.link;

/**
 * https://leetcode.cn/problems/rotate-list
 * 旋转链表 平移链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 */
public class Solution06 {
    /**
     * 首先计算出链表的长度n》找到该链表的末尾节点
     * 》将其与头节点相连，这样就得到了闭合为环的链表。
     * 找到新链表的最后一个节点，即原链表的第 (n−1)−(k%n)个节点，n从0开始
     * 将当前闭合为环的链表断开，即可得到我们所需要的结果。
     * 特殊情况，链表长度=1，或移动k为n的倍数时，新链表与原链表相同
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 链表长度=1 新链表与原链表相同
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        // 计算链表长度
        int cnt = 1;
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
            cnt++;
        }
        // 移动k为n的倍数 新链表与原链表相同
        if ((k % cnt) == 0) return head;
        // 连接成环
        p.next = head;

        // 断点位置
        int breakNum = cnt - k % cnt;
        while (breakNum-- > 0) {
            p = p.next;
        }

        ListNode ans = p.next;
        p.next = null;
        return ans;
    }
}
