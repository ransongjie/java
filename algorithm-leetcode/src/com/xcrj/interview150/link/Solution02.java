package com.xcrj.interview150.link;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer
 * 复制带随机指针的链表
 */
public class Solution02 {

    /**
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        // 新结点->拷贝结点->新结点->拷贝结点
        Node p = head;
        while (p != null) {
            Node p2 = new Node(p.val);
            p2.next = p.next;
            p.next = p2;
            p = p.next.next;
        }

        // 操作随机指针
        p = head;
        while (p != null) {
            if (p.random != null) {
                Node p2 = p.next;
                Node p2rand = p.random.next;
                p2.random = p2rand;
            }
            p = p.next.next;
        }

        // 切开新旧结点
        p = head;
        Node cpHead = head.next, p2;
        while (p != null) {//
            p2 = p.next;
            p.next = p.next.next;//p.next.next的极端情况就是null
            p2.next = p2.next != null ? p2.next.next : null;//p2.next可能为null，原链表只有1个结点时
            p = p.next;//
        }

        return cpHead;
    }

    /**
     * @param head
     * @return
     */
    public static Node copyRandomList2(Node head) {
        if (head == null) return null;
        //map<原结点,拷贝结点>
        Map<Node, Node> map = new HashMap<>();
        Node cpHead = new Node(head.val);
        map.put(head, cpHead);
        Node p = head.next;
        while (p != null) {
            Node node = new Node(p.val);
            map.put(p, node);
            p = p.next;
        }

        //处理next指针
        p = head;
        Node q = cpHead;
        while (p.next != null) {//q每次和下一个结点相连
            Node nxt = map.get(p.next);
            q.next = nxt;
            q = q.next;
            p = p.next;
        }

        //处理random指针
        p = head;
        q = cpHead;
        while (p != null) {//p遍历链表连接随机指针
            if (p.random != null) {
                Node mr = map.get(p.random);
                q.random = mr;
            }
            p = p.next;
            q = q.next;
        }

        return cpHead;
    }
}
