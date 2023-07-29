package com.xcrj.basic.pass1.tree;
//平衡二叉树
public class IsAVLTree {
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
        Node root=node1;
        // Util.printTree(root);
        //
        System.out.println(aBBT(root).aBBT);
    }

    /**
     * 后续遍历
     *
     * 左子树
     * - 是否平衡二叉树
     * - 子树高度
     * 右子树
     * - 是否平衡二叉树
     * - 子树高度
     * 当前结点
     * - 是否平衡二叉树
     * - 高度
     * @param root
     */
    public static We aBBT(Node root) {
        if(root==null) return new We(0,true);
        //左子树
        We left=aBBT(root.left);
        //右子树
        We right=aBBT(root.right);
        //当前结点
        int height=Math.max(left.height, right.height)+1;
        boolean aBBT=true;
        if(!left.aBBT){
            aBBT=false;
        }
        if(!right.aBBT){
            aBBT=false;
        }
        if(Math.abs(left.height-right.height)>1){
            aBBT=false;
        }
        return new We(height,aBBT);
    }

    static class We{
        int height;
        boolean aBBT;
        public We(){}
        public We(int height,boolean aBBT){
            this.height=height;
            this.aBBT=aBBT;
        }
    }
}
