package com.xcrj.basic.pass2.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CompleteBinaryTree {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        Node node8=new Node(8);
        Node node9=new Node(9);
        Node node10=new Node(10);
        Node node11=new Node(11);
        Node node12=new Node(12);
        node1.left=node2;node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;node3.right=node7;
        node4.left=node8;node4.right=node9;node5.left=node10;node5.right=node11;
        node6.left=node12;

        Util.printTree(node1);

        System.out.println(completeBinaryTree(node1));
    }   
    
    /**
     * 1. 有右无左 return false
     * 2. 有左无右 开关变量
     * 3. 之后的结点必须既无左又无右
     */
    public static boolean completeBinaryTree(Node n) {
        Queue<Node> que=new LinkedList<>();
        Node p=n;
        que.offer(p);
        boolean flag=false;
        while(!que.isEmpty()){
            p=que.poll();
            if(p.right!=null&&p.left==null) return false;
            if(flag&&(p.left!=null||p.right!=null)) return false;//
            if(p.left!=null&&p.right==null) flag=true;
            if(p.left!=null){
                que.offer(p.left);
            }
            if(p.right!=null){
                que.offer(p.right);
            }
        }   

        return true;
    }
}
