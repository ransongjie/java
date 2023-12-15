package com.xcrj.basic.pass2.tree;

public class PreOrderSerialize {
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
        System.out.println(pods(node1));
    }

    // 考察二叉树的递归会经过同一层3次
    public static String pods(Node n) {
        if(n==null) return "#_";
        StringBuilder sb=new StringBuilder();
        sb.append(n.val).append("_");
        String left=pods(n.left);
        String right=pods(n.right);
        sb.append(left).append(right);
        return sb.toString();
    }
}
