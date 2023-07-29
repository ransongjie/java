package com.xcrj.basic.pass2.link;
//两个有序链表的公共部分
public class L2Public {
    public static void main(String[] args) {
        Node h1=new Node(1);
        Node a2=new Node(2);
        Node a3=new Node(5);
        h1.next=a2;a2.next=a3;

        Node h2=new Node(2);
        Node b2=new Node(3);
        Node b3=new Node(5);
        h2.next=b2;b2.next=b3;

        l2Public(h1, h2);
    }

    /**
     * 其中1个链表是否到头
     * 指针指向结点的值是否相等
     * @param n1
     * @param n2
     */
    public static void l2Public(Node n1,Node n2) {
        if(n1==null) return;
        if(n2==null) return;

        Node p1=n1;
        Node p2=n2;
        while(p1!=null&&p2!=null){
            if(p1.val<p2.val){
                p1=p1.next;
                continue;
            }
            if(p2.val<p1.val){
                p2=p2.next;
                continue;
            }
            if(p1.val==p2.val){
                System.out.println(p1.val);
                p1=p1.next;
                p2=p2.next;
                continue;
            }
        }
    }
}
