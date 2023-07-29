package com.xcrj.basic.pass2.tree;

public class BalanceBinaryTree {
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
        node1.left=node2;node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;node3.right=node7;
        node4.left=node8;
        node8.left=node9;
        // Util.printTree(node1);
        //
        System.out.println(balanceBinaryTree(node1));
    }

    static class We{
        boolean isBalanced;
        int height;
        public We(){}
        public We(boolean isBalanced, int height){
            this.isBalanced=isBalanced;
            this.height=height;
        }
    }

    public static boolean balanceBinaryTree(Node n) {
        if(n==null) return false;
        return proccess(n).isBalanced;
    }

    private static We proccess(Node n){
        if(n==null) return new We(true,0);//
        We left=proccess(n.left);
        We right=proccess(n.right);
        int height=Math.max(left.height, right.height)+1;
        boolean isBalanced=left.isBalanced&&right.isBalanced;
        if(Math.abs(right.height-left.height)>1) isBalanced=false;
        return new We(isBalanced,height);
    }
}
