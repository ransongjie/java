package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 028. 展平多级双向链表，子链表插入到父链表中间
 * 多级双向链表: 三个指针的链表：next prev child
 * 扁平化链表（首尾相连）：将多指针链表 转化为 普通的双向链表
 * <p>
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。
 */
public class Solution28 {

    /**
     * 深度优先
     * 将子链表插入第m结点和第m+1j结点之间
     */
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    /**
     * 深度优先 child存在则走child
     */
    public Node dfs(Node node) {
        Node p = node;
        // 记录子链表的最后1个结点
        Node l = null;
        while (p != null) {
            // 存在子链表 走子链表
            if (p.child != null) {
                // 子链表尾部结点
                Node cl = dfs(p.child);
                // 把子链表插到两结点中间，第m个结点和第m+1个结点中间，第m+1个结点可能为null
                // next不为null，连接两次，子链表的头结点被连接1次，子链表的尾部结点被连接1次
                if (p.next != null) {
                    Node pnext = p.next;
                    p.next = p.child;
                    p.child.prev = p;
                    cl.next = pnext;
                    pnext.prev = cl;
                    p.child = null;
                } else {
                    // next为null，连接一次，子链表的头结点被连接1次
                    p.next = p.child;
                    p.child.prev = p;
                    p.child = null;
                }
                // 父子链表合并，产生新的尾部
                l = cl;
            } else {
                // 记录上1个结点，再看下1个结点是否存在子链表
                l = p;
            }
            p = p.next;
        }

        return l;
    }


}
