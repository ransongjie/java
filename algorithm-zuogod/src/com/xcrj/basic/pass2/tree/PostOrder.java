package com.xcrj.basic.pass2.tree;

import java.util.Stack;

//后序遍历 左右根
public class PostOrder {
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
        // postOrder1(node1);
        postOrder2(node1);
    }

    public static void postOrder1(Node n) {
        if(n==null) return;
        postOrder1(n.left);
        postOrder1(n.right);
        System.out.println(n.val);
    }

    //入栈顺序 根左右 到 另一个栈 左右根
    public static void postOrder2(Node r) {
        if(r==null) return;
        Node p=r;
        Stack<Node> s=new Stack<>();
        Stack<Node> s2=new Stack<>();
        s.push(p);
        while(!s.isEmpty()){
            p=s.pop();
            s2.push(p);
            if(p.left!=null) s.push(p.left);
            if(p.right!=null) s.push(p.right);
        }

        while(!s2.isEmpty()){
            p=s2.pop();
            System.out.println(p.val);
        }
    }
}
