package com.xcrj.basic.pass2.tree;

import java.util.Stack;

//中序遍历 左根右
public class InOrder {
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
        // inOrder1(node1);
        inOrder2(node1);
    }

    public static void inOrder1(Node n) {
        if(n==null) return;
        inOrder1(n.left);
        System.out.println(n.val);
        inOrder1(n.right);
    }

    //入栈顺序 在孩子不为空一直入左孩子
    public static void inOrder2(Node r) {
        if(r==null) return;
        Node p=r;
        Stack<Node> s=new Stack<>();
        while(!s.isEmpty()||p!=null){//
            if(p!=null){
                s.push(p);
                p=p.left;
            }else{
                p=s.pop();
                System.out.println(p.val);
                p=p.right;//
            }
        }
    }
}
