package com.xcrj.basic.pass1.tree;

import java.util.LinkedList;
import java.util.Queue;

//完全二叉树
public class IsCompleteBinaryTree {
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
        // node6.right=node12
        // node7.left=new Node();

        Node root=node1;
        //Util.printTree(root);
        System.out.println(acbt(root));
    }

    /**
     * 宽度优先遍历
     * 1. 有右无左，返回false
     * 2. 有左无右，后续结点必须全是叶子结点
     * @param root
     * @return
     */
    public static boolean acbt(Node root) {
        if(root==null) return true;
        Queue<Node> que=new LinkedList<>();
        que.offer(root);
        boolean open=false;//开关
        while(!que.isEmpty()){
            Node n=que.poll();
            //
            if(n.right!=null&&n.left==null) return false;
            if(open&&(n.left!=null||n.right!=null)) return false;//上下两行次序
            if(n.right==null&&n.left!=null) open=true;
            //
            if(n.left!=null) que.offer(n.left);
            if(n.right!=null) que.offer(n.right);
        }
        return true;
    }
}