package com.xcrj.basic.pass2.tree;

import java.util.LinkedList;
import java.util.Queue;

//宽度/广度优先
public class WidthOrder {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        //
        node1.left=node2; node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;node3.right=node7;
        node4.left=node4.right=null;node5.left=node5.right=null;node6.left=node6.right=null;node7.left=node7.right=null;
        //
        widthOrder(node1);
    }

    public static void widthOrder(Node n) {
        if(n==null) return;
        Queue<Node> que=new LinkedList<>();
        Node p=n;
        que.offer(p);
        while(!que.isEmpty()){
            p=que.poll();
            System.out.println(p.val);
            if(p.left!=null) que.offer(p.left);
            if(p.right!=null) que.offer(p.right);
        }
    }
}
