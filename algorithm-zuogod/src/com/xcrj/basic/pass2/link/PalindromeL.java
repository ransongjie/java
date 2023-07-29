package com.xcrj.basic.pass2.link;

import java.util.Stack;

//回文链表
public class PalindromeL {
    public static void main(String[] args) {
        // Node h=new Node(1);
        // Node n1=new Node(2);
        // Node n2=new Node(3);
        // Node n3=new Node(2);
        // Node n4=new Node(1);
        // h.next=n1;n1.next=n2;n2.next=n3;n3.next=n4;
        // System.out.println(palindromeL1(h));
        // System.out.println(palindromeL2(h));

        // Node h=new Node(1);
        // Node n1=new Node(2);
        // Node n2=new Node(2);
        // Node n3=new Node(1);
        // h.next=n1;n1.next=n2;n2.next=n3;
        // System.out.println(palindromeL1(h));
        // System.out.println(palindromeL2(h));

        int times=10000;
        int maxLen=10;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxLen, maxV);
            Node dummy=new Node();
            Node p=dummy;
            for (int a : as) {
                Node n=new Node(a);
                p.next=n;
                p=p.next;
            }
            boolean b1=palindromeL1(dummy.next);
            boolean b2=palindromeL2(dummy.next);
            if(b1!=b2){
                System.out.println("not good");
                System.out.println(b1);
                System.out.println(b2);
                throw new RuntimeException();
            }
        }
    }

    //栈
    public static boolean palindromeL1(Node h) {
        if(h==null) return false;
        Stack<Integer> s=new Stack<>();
        Node p=h;
        while(p!=null){
            s.push(p.val);
            p=p.next;
        }

        p=h;
        while(p!=null){
            Integer v=s.pop();
            if(p.val!=v){
                return false;
            }
            p=p.next;
        }
        return true;
    }

    //
    public static boolean palindromeL2(Node h) {
        if(h==null) return false;
        if(h.next==null) return true;
        Node mid=midNode(h);
        Node h2=reverseL(mid.next);
        
        Node p1=h;
        Node p2=h2;
        while(p1!=p2&&p1!=null&&p2!=null){
            if(p1.val!=p2.val) return false;
            p1=p1.next;
            p2=p2.next;
        }

        h2=reverseL(h2);
        mid.next=h2;//

        return true;
    }

    /**
     * 快慢指针 获取链表中点
     * 奇数 （len/2）+1
     * 偶数 （len/2）
     */
    public static Node midNode(Node h) {
        Node p=h;
        Node fast=p;
        Node slow=p;
        while(fast!=null&&fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /**
     * 翻转链表 
     * @param h
     * @return 新的头结点
     */
    public static Node reverseL(Node h) {
        if(h.next==null) return h;//找到了新的头结点

        Node newH=reverseL(h.next);
        h.next.next=h;
        h.next=null;//
        return newH;
    }

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
