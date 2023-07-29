package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 028. 展平多级双向链表，子链表插入到父链表中间
 * 多级双向链表: 三个指针的链表：next prev child
 */
public class Solution28 {
    /**
     * 深度优先插入子链表
     */
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    /**
     * 深度优先从最后1个子链表开始合并
     * @param node
     */
    public Node dfs(Node node){
        Node p=node;
        // 链表的最后一个结点
        Node last=null;
        // 遍历链表
        while(null!=p){
            // 寻找有子链表的结点
            if(null!=p.child){
                // 有子链表 深度优先 获取子链表的最后一个结点
                Node childLast=dfs(p.child);
                // p结点不是父链表的最后1个结点
                if(null!=p.next){
                    Node pNext=p.next;
                    // p<->pChild
                    p.next=p.child;
                    p.child.prev=p;
                    // pNext<->l
                    childLast.next=pNext;
                    pNext.prev=childLast;
                    // 置空子链表
                    p.child=null;
                }
                // p结点是父链表的最后1个结点
                else{
                    // p<->pChild
                    p.next=p.child;
                    p.child.prev=p;
                    // 置空子链表
                    p.child=null;
                }
                // 子链表的最后1个结点作为该链表的最后1个结点
                last=childLast;
            }else{
                // 当前结点作为该链表的最后1个结点
                last=p;
            }
            p=p.next;
        }

        return last;
    }
}
