package com.xcrj.offer_special.pass4;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表
 * 1 2 3 4
 * 1 4 2 3
 * 下标加起来是n
 */
public class Solution026 {
    /**
     * 利用线性表的可随机访问
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        ListNode p = head;
        List<ListNode> list = new ArrayList<>();
        while (p != null) {
            list.add(p);
            p = p.next;
        }
        //
        int i = 0, j = list.size() - 1;
        ListNode pi = null, pj = null;
        while (i < j) {
            pi = list.get(i);
            pj = list.get(j);
            pi.next = pj;
            pj.next = null;
            i++;
            if (i == j) {
                break;
            }
            pi = list.get(i);
            pj.next = pi;
            pi.next = null;
            j--;
        }
    }
}
