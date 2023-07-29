package com.xcrj.basic.pass2.tree;

import java.util.Stack;

//前序遍历 根左右
//重点在非递归版
public class PreOrder {
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
        // preOrder1(node1);
        // preOrder2(node1);
    }

    public static void preOrder1(Node n) {
        if(n==null) return;
        System.out.println(n.val);
        preOrder1(n.left);
        preOrder1(n.right);
    }

    //入栈顺序 根右左
    public static void preOrder2(Node r) {
        if(r==null) return;
        Node p=r;
        Stack<Node> s=new Stack<>();
        s.push(p);
        while(!s.isEmpty()){
            p=s.pop();
            System.out.println(p.val);
            if(p.right!=null) s.push(p.right);
            if(p.left!=null) s.push(p.left);
        }
    }
}
