package com.xcrj.basic.pass1.link;

import java.util.Arrays;
import java.util.Stack;

//回文链表
public class PalindromeL {
    public static void main(String[]args) {
        // int[]as={1,2,3,4,4,3,2,1};
        // int[]as={1,2,1,3,2,1};
        // int[]as={1,1,1};
        // int[]as={};
        // Node d=new Node();
        // Node p=d;
        // for (int i = 0; i < as.length; i++) {
        //     Node node=new Node(as[i],null);
        //     p.next=node;
        //     p=p.next;
        // }
        // System.out.println(palin1(d.next));
        // System.out.println(palin2(d.next));
        // System.out.println();

        int times=100000;
        int maxLen=1000;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxLen, maxV);
            Node d=new Node();
            Node p=d;
            for (int j = 0; j < as.length; j++) {
                Node node=new Node(as[j],null);
                p.next=node;
                p=p.next;
            }
            boolean aa=palin1(d.next);
            boolean bb=palin2(d.next);
            if(aa!=bb){
                System.out.println("not good");
                System.out.println(Arrays.toString(as));
            }
        }
    }

    //笔试
    public static boolean palin1(Node head) {
        if(head==null) return false;
        Stack<Integer> s=new Stack<>();
        Node p=head;
        while(p!=null){
            s.push(p.val);
            p=p.next;
        }
        p=head;
        while(p!=null){
            if(p.val!=s.pop()){
                return false;
            }
            p=p.next;
        }
        
        return true;
    }

    //面试
    public static boolean palin2(Node head) {
        if(head==null){
            return false;
        }
        if(head.next==null){
            return true;
        }
        Node slow=head;
        Node fast=head;
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==null) break;
        }

        Node head2=reverse(slow);
        
        Node p=head;
        Node q=head2;
        while(p!=null&&q!=null&&p!=q){
            if(p.val!=q.val){
                reverse(head2);//
                return false;
            }
            p=p.next;
            q=q.next;
        }
        
        reverse(head2);

        return true;
    }

    public static Node reverse(Node head) {
        if(head==null) return null;
        if(head.next==null) return head;//找到新头退出

        Node newHead=reverse(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        int[] as=new int[len];
        for (int i = 0; i < as.length; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
