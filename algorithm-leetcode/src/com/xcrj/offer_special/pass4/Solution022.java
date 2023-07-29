package com.xcrj.offer_special.pass4;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表中环的入口节点
 */
public class Solution022 {
    /**
     * set记录
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (set.contains(p)) {
                return p;
            } else {
                set.add(p);
            }
            p = p.next;
        }
        return null;
    }
}
