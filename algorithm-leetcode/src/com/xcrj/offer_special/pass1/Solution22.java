package com.xcrj.offer_special.pass1;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 022. 链表中环的入口节点
 * 给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
 * 链表有没有环，环的入口结点是，环是从尾部结点next指向前面的某个结点（环入口结点）
 */
public class Solution22 {
    /**
     * 有环证明以前访问过
     * 使用hash表记录之前访问过的结果
     */
    public ListNode detectCycle1(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<ListNode> visited = new HashSet<>(3);
        ListNode p = head;
        while (p.next != null) {
            if (visited.contains(p)) {
                return p;
            } else {
                visited.add(p);
            }
            p = p.next;
        }

        return null;
    }

    /**
     * 快慢指针，两次相遇，第一次slow和fast相遇，第二次slow和p相遇得到环入口结点
     * <p>
     * 式子1：
     * slow指针走一步 fast指针走两步 因此fast是slow的两倍速
     * 所以，2*slow=fast
     * <p>
     * 式子2：
     * 设环外距离为a，slow指针环内移动距离为b，环距离总长为b+c，
     * slow指针移动距离为 a+b，slow指针在环内走了b步
     * fast指针移动距离为 a+n(b+c)+b，fast指针绕环n圈并走了b步之后与slow相遇
     * <p>
     * 式子1、式子2：
     * 所以，2*(a+b)=a+n(b+c)+b
     * ！！！知道，当slow指针和fast指针相遇之后，求环入口结点，即求a的长度
     * 根据上式推出 a=c+(n-1)(b+c)即 当slow和fast相遇之后，指针再走c加上n-1圈就可以得到环入口结点，slow指针再走c加上n-1圈就可以得到环入口结点
     */
    public ListNode detectCycle2(ListNode head) {
        // 特殊情况处理
        if (head == null) {
            return null;
        }
        // slow fast指针移动
        ListNode slow = head;
        ListNode fast = head;
        // 因为fast走的块
        while (fast != null) {
            slow = slow.next;
            // !!next不为nullfast才有next.next
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            // slow和fast相遇
            if (slow == fast) {
                // ！！！相遇之后slow走c+(n-1)圈=a，即让slow从相遇点开始走c+(n-1)(b+c)，让p从head开始走a，slow和p相遇
                ListNode p = head;
                // slow和p相遇
                while (slow != p) {
                    slow = slow.next;
                    p = p.next;
                }
                return p;
            }
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
