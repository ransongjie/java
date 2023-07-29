package com.xcrj.basic.pass2.tree;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 递归内部变量会参与递归
 */
public class PreOrderDDeserialize {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        node1.left=node2; node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;
        node3.right=node7;
        node4.left=node4.right=null;node5.left=node5.right=null;node6.left=node6.right=null;
        node7.left=node7.right=null;
        Util.printTree(node1);
        String s=PreOrderSerialize.pods(node1);
        System.out.println(s);
        Node n=podd(s);
        Util.printTree(n);
        Node n2=podd2(s);
        Util.printTree(n2);
    }

    public static Node podd(String str) {
        String[] ss=str.split("_");
        return proccess(ss);
    }

    static int i=0;//
    private static Node proccess(String[] ss){
        if(ss[i].equals("#")) return null;
        Node n=new Node(Integer.valueOf(ss[i]));
        i++;
        Node left=proccess(ss);
        i++;
        Node right=proccess(ss);
        n.left=left;
        n.right=right;
        return n;
    }

    public static Node podd2(String str) {
        String[] ss=str.split("_");
        Queue<String> que=new LinkedList<>();
        for(String s:ss){
            que.offer(s);
        }
        return proccess2(que);
    }

    private static Node proccess2(Queue<String> que){
        if(que.isEmpty()) return null;
        String s=que.poll();//
        if(s.equals("#")) return null;
        Node n=new Node(Integer.valueOf(s));
        Node left=proccess2(que);
        Node right=proccess2(que);
        n.left=left;
        n.right=right;
        return n;
    }
}
