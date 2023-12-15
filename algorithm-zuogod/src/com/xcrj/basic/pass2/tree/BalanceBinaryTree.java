package com.xcrj.basic.pass2.tree;

/**
 * 平衡二叉树也叫AVL树，它或者是一颗空树，或者具有以下性质的二叉排序树：
 * 它的左子树和左子树的高度之差(平衡因子)的绝对值不超过1，
 * 且它的左子树和右子树都是一颗平衡二叉树。
 */
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

    // 后续遍历
    private static We proccess(Node n){
        // 空结点是平衡二叉树
        if(n==null) return new We(true,0);
        We left=proccess(n.left);
        We right=proccess(n.right);
        // 当前子树高度
        int height=Math.max(left.height, right.height)+1;
        // 左右子树是否平衡二叉树
        boolean isBalanced=left.isBalanced&&right.isBalanced;
        // 左右子树高度差?<=1
        if(Math.abs(right.height-left.height)>1) isBalanced=false;

        return new We(isBalanced,height);
    }
}
