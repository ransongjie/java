package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 029. 排序的循环链表
 * 插入新结点 仍然是循环升序的链表
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素insertVal ，使这个列表仍然是循环升序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 */
public class Solution29 {
    /**
     * 循环链表只需要使用next指针
     * 插入点前后结点 因此使用两个指针
     * 分治法：升序循环链表插入新结点。分治法（一般情况，特殊情况，最后情况），前后指针插入新结点，pre和next指针
     * - insertVal>=preVal && insertVal<=nextVal：例如，7->9，插入8,7->8->9, 一般情况
     * - preVal>nextVal && insertVal>preVal: 例如，8->2, 插入9, 8->9->2,
     * - preVal>nextVal && insertVal<nextVal：例如，8->2, 插入1, 8->1->2，
     * - preVal=nextVal：例如，3->3, 插入0, 3->3->0，遍历完链表所有结点都相等，插入到尾部
     */
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        // 1个结点都没有
        if (head == null) {
            node.next = node;
            return node;
        }
        // 循环链表中只有1个结点
        if (head.next == head) {
            head.next = node;
            node.next = head;
            return head;
        }
        // 插入结点前后结点指针
        Node p = head;
        Node pNext = head.next;
        // 循环1圈寻找插入位置
        while (pNext != head) {
            if (insertVal >= p.val && insertVal <= pNext.val) {
                p.next = node;
                node.next = pNext;
                return head;
            }
            if (p.val >pNext.val && insertVal > p.val) {
                p.next = node;
                node.next = pNext;
                return head;
            }
            if (p.val >pNext.val && insertVal < pNext.val) {
                p.next = node;
                node.next = pNext;
                return head;
            }

            p = p.next;
            pNext = pNext.next;
        }
        // 遍历完链表所有结点都相等，插入到尾部, preVal=nextVal：例如，3->3, 插入0, 3->3->0，插入在尾部
        p.next = node;
        node.next = pNext;

        return head;
    }
}
