package com.xcrj.offer_special.pass4;

/**
 * 展平多级双向链表，子链表插入到父链表中间
 */
public class Solution028 {

    public Node flatten(Node head) {
        dfs(head);
        //没有使用dfs的返回值
        return head;
    }

    /**
     * 深度优先
     * 有子链表走子，p，找子链表入口结点
     * 无子链表走下，l，找子链表尾部结点
     * 利用p，l插入子链表
     */
    private Node dfs(Node p) {
        Node l = null, tmp = null;
        while (p != null) {
            if (p.child != null) {
                l = dfs(p.child);
                //子链表插入到父链表尾部
                if (p.next == null) {
                    p.next = p.child;
                    p.child.prev = p;
                    p.child = null;
                }
                //子链表插入到父链表中间
                else {
                    tmp = p.next;
                    p.next = p.child;
                    p.child.prev = p;
                    p.child = null;
                    l.next = tmp;
                    tmp.prev = l;
                }
            } else {
                l = p;
            }
            p = p.next;
        }
        return l;
    }
}
