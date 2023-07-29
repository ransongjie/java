package com.xcrj.basic.pass1.tree;

import java.util.Stack;

//后序遍历
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
        //
        Node root=node1;
        //
        // postOrder(root);
        postOrder2(root);
    }
    //（先序遍历）根左右 > 出栈 根右左 > 另一个栈 > 出栈 左右根
    public static void postOrder(Node root) {
        if(root==null) return;
        Stack<Node> s1=new Stack<>();
        Stack<Node> s2=new Stack<>();
        s1.push(root);
        while(!s1.isEmpty()){
            Node n=s1.pop();
            s2.push(n);
            if(n.left!=null) s1.push(n.left);
            if(n.right!=null) s1.push(n.right);
        }

        while(!s2.isEmpty()){
            System.out.println(s2.pop().val);
        }
    }

    public static void postOrder2(Node node) {
        if(node==null) return;
        //
        postOrder2(node.left);
        //
        postOrder2(node.right);
        //
        System.out.println(node.val);
    }
}
