package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 029. 排序的循环链表
 * 插入新结点 仍然是循环升序的链表
 * xcrj 单循环链表
 */
public class Solution29 {
    public Node insert(Node head, int insertVal) {
        Node node=new Node(insertVal);
        // 循环链表中1个结点都没有，创建单节点循环链表返回
        if(null==head){
            node.next=node;
            return node;
        }

        // 循环链表中只有1个结点
        if(head==head.next){
            head.next=node;
            node.next=head;
            return head;
        }

        /**
         * 循环链表中有多个结点
         * 分情况讨论
         * - 7>9，插入8，插到中间，7>8>9
         * - 8>2，插入9，插到尾部，8>9>2
         * - 8>2，插入1，插到开头，8>1>2
         * - 3>3，插入1，插到尾部，3>3>1
         */
        Node p=head;
        Node pNext=p.next;
        while(head!=pNext){
            // 7>9，插入8，插到中间，7>8>9
            if(insertVal>=p.val&&insertVal<=pNext.val){
                p.next=node;
                node.next=pNext;
                return head;
            }
            // 8>2，插入9，插到尾部，8>9>2
            if(p.val>pNext.val&&insertVal>p.val){
                p.next=node;
                node.next=pNext;
                return head;
            }
            // 8>2，插入1，插到开头，8>1>2
            if(p.val>pNext.val&&insertVal<pNext.val){
                p.next=node;
                node.next=pNext;
                return head;
            }
            
            p=p.next;
            pNext=pNext.next;
        }
        // 3>3，插入1，插到尾部，3>3>1
        p.next=node;
        // xcrj
        node.next=pNext;
        return head;
    }
}
