package com.xcrj.basic.pass1.link;

import java.util.HashSet;
import java.util.Set;

//链表环的入口结点
public class Circle {
    public static void main(String[] args) {
        // Node node1=new Node(1);
        // Node node2=new Node(2);
        // Node node3=new Node(3);
        // // Node node4=new Node(4);
        // node1.next=node2;
        // // node2.next=node1;
        // node2.next=node3;
        // // node3.next=node4;
        // // node4.next=node2;
        // Node head=node1;
        // // Node one=circle1(head);
        // Node one=circle2(head);
        // System.out.println(one==null?null:one.val);

        int times=100000;
        int maxLen=1000;
        for (int i = 0; i < times; i++) {
            Node head=getCircle(maxLen);
            Node c1=circle1(head);
            Node c2=circle2(head);
            if(c1!=c2){
                System.out.println("not good");
            }
        }
    }

    public static Node circle1(Node head) {
        Set<Node>set=new HashSet<>();
        Node p=head;
        while(p!=null){
            if(set.contains(p)){
                return p;
            }
            set.add(p);
            p=p.next;
        }
        return null;
    }

    //
    public static Node circle2(Node head) {
        if(head==null) return null;
        if(head.next==null) return null;
        Node slow=head;
        Node fast=head;
        while(fast.next!=null){//
            slow=slow.next;
            fast=fast.next.next;
            if(fast==null) return null;//
            if(slow==fast){
                break;
            }
        }
        if(fast.next==null) return null;

        Node p=head;
        while(p!=slow){
            p=p.next;
            slow=slow.next;
        }
        return p;
    }

    public static Node getCircle(int maxLen) {
        int len=(int)(Math.random()*maxLen);
        Node d=new Node();
        Node p=d;
        for (int i = 0; i < len; i++) {
            Node node=new Node(i);
            p.next=node;
            p=p.next;
        }

        int circleIn=(int)(Math.random()*maxLen);
        Node q=d.next;
        while(q!=null){
            if(q.val==circleIn){
                p.next=q;
                break;
            }
            q=q.next;
        }

        return d.next;
    }
}
