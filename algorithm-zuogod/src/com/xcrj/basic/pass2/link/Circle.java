package com.xcrj.basic.pass2.link;

import java.util.HashSet;
import java.util.Set;

//链表环的入口结点
public class Circle {
    public static void main(String[] args) {
        // Node n1=new Node(1);
        // Node n2=new Node(2);
        // Node n3=new Node(3);
        // Node n4=new Node(4);
        // n1.next=n2;n2.next=n3;n3.next=n4;
        // n4.next=n2;
        // // Node c=circle1(n1);
        // Node c=circle2(n1);
        // System.out.println(c.val);

        int times=1000;
        int maxLen=100;
        for (int i = 0; i < times; i++) {
            Node h=getCircle(maxLen);
            Node c1=circle1(h);
            Node c2=circle2(h);
            if(c1!=c2){
                System.out.println("not good");
                System.out.println(c1.val);
                System.out.println(c2.val);
                throw new RuntimeException();
            }
        }
    }

    public static Node circle1(Node h) {
        if(h==null) return null;
        Node p=h;
        Set<Node> set=new HashSet<>();
        while(p!=null){
            if(set.contains(p)){
                return p;
            }
            set.add(p);
            p=p.next;
        }

        return null;
    }

    public static Node circle2(Node h) {
        if(h==null) return null;
        Node fast=h;
        Node slow=h;
        while(fast!=null&&fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                break;
            }
        }
        
        if(fast==null||fast.next==null||fast.next.next==null) return null;//

        Node p=h;
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
