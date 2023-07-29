package com.xcrj.basic.pass1.tree;

import java.util.Stack;

//前序遍历
public class Preorder{
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
         preorder(root);
        System.out.println("==========");
        preorder2(root);
        System.out.println("==========");
        preorder3(root);
    }
    //根右左
    public static void preorder(Node root) {
        if(root==null) return;
        Stack<Node> s=new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            Node n=s.pop();
            System.out.println(n.val);
            if(n.right!=null) s.push(n.right);
            if(n.left!=null) s.push(n.left);
        }
    }
    
    //err
    public static void preorder3(Node root) {
        if(root==null) return;
        Stack<Node> s=new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            Node n=s.pop();
            System.out.println(n.val);
            if(n.left!=null){
                n=n.left;
                s.push(n);
            }else if(n.right!=null){
                n=n.right;
                s.push(n);
            }
        }
    }

    public static void preorder2(Node node) {
        if(node==null)return;
        System.out.println(node.val);
        preorder2(node.left);
        preorder2(node.right);
    }
}