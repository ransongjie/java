package com.xcrj.basic.pass1.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//前序遍历反序列化
public class PreOrderDeserialize {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        // Node node7=new Node(7);
        //
        node1.left=node2; node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;
        // node3.right=node7;
        node4.left=node4.right=null;node5.left=node5.right=null;node6.left=node6.right=null;
        // node7.left=node7.right=null;
        //
        Node root=node1;
        Util.printTree(root);
        String str=PreOrderSerialize.serialize2(root);
        System.out.println(str);
        //
        String[] strs=str.split("_");
        System.out.println(Arrays.toString(strs));
        //
        // Node n=deserialize(strs);
        Queue<String> que=new LinkedList<>();
        for(String s:strs){
            que.offer(s);
        }
        Node n=deserialize2(que);
        Util.printTree(n);
    }

    static int i=0;
    public static Node deserialize(String[] strs) {
        if(strs[i].equals("#")) return null;
        Node n=new Node(Integer.valueOf(strs[i])); 
        i++;
        n.left=deserialize(strs);
        i++;
        n.right=deserialize(strs);
        return n;
    }

    public static Node deserialize2(Queue<String> que) {
        String s=que.poll();
        if(s.equals("#"))return null;
        Node n=new Node(Integer.valueOf(s));
        n.left=deserialize2(que);
        n.right=deserialize2(que);
        return n;
    }
}
