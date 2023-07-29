package com.xcrj.offer_special.pass4;

/**
 * 排序的循环链表
 */
public class Solution029 {
    /**
     * 先创建结点
     * 再在各种情况下插入
     *
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);
        //循环链表中有空结点
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }
        //循环链表中有1个结点
        if (head.next == null) {
            head.next = insertNode;
            insertNode.next = head;
            return head;
        }
        //循环链表中有多个结点
        Node p = head, pNext = p.next;
        //pNext非尾结点
        while (pNext != head) {
            if (p.val <= pNext.val) {
                /**
                 * 7->9，插入8，7->8->9
                 * 7->7，7，7->7->7
                 * 7->9，7，7->7->9
                 * 7->9，9，7->9->9
                 */
                if (p.val <= insertVal && insertVal <= pNext.val) {
                    p.next = insertNode;
                    insertNode.next = pNext;
                    return head;
                }
            } else {
                /**
                 * 8->2，9，8->9->2
                 * 8->2，1，8->1->2
                 */
                if (p.val < insertVal && insertVal > pNext.val) {
                    p.next = insertNode;
                    insertNode.next = pNext;
                    return head;
                }
                if (p.val > insertVal && insertVal < pNext.val) {
                    p.next = insertNode;
                    insertNode.next = pNext;
                    return head;
                }
            }
            p = pNext;
            pNext = pNext.next;
        }
        //pNext=head
        p.next = insertNode;
        insertNode.next = pNext;
        return head;
    }
}
