package com.xcrj.basic.pass2.tree;

//满二叉树
public class FullBinaryTree {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        // Node node7=new Node(7);
        node1.left=node2;node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;
        // node3.right=node7;
        // Util.printTree(node1);
        //
        System.out.println(fullBinaryTree(node1));
        System.out.println(fullBinaryTree2(node1));
    }

    static class Element{
        boolean isFull;
        // 利用高度和结点数量关系=2^h-1=num
        int height;
        int num;
        public Element() {
        }
        public Element(boolean isFull,int height,int num) {
            this.isFull=isFull;
            this.height=height;
            this.num=num;
        }   
    }
    public static boolean fullBinaryTree(Node n) {
        if(n==null) return false;
        return proccess(n).isFull;
    }

    public static Element proccess(Node n) {
        if(n==null) return null;
        Element left=proccess(n.left);
        Element right=proccess(n.right);

        // 1个结点是满二叉树
        boolean isFull=true;
        int height=1;
        int num=1;

        if(left!=null){
            num+=left.num;// 加上左子树的结点
            height=Math.max(height,left.height+1);// 高度=max(leftH,rightH,currentH)
        }
        if(right!=null){
            num+=right.num;
            height=Math.max(height,right.height+1);//
        }
        isFull=(1<<height)-1==num?true:false;

        return new Element(isFull,height,num);
    }

    static class Element2{
        int height;
        int num;
        public Element2() {
        }
        public Element2(int height,int num) {
            this.height=height;
            this.num=num;
        }  
    }

    public static boolean fullBinaryTree2(Node n) {
        if(n==null) return false;
        Element2 e=procces2(n);
        return (1<<e.height)-1==e.num;
    }

    public static Element2 procces2(Node n) {
        if(n==null) return new Element2(0,0);
        Element2 left=procces2(n.left);
        Element2 right=procces2(n.right);
        int height=Math.max(left.height, right.height)+1;
        int num=left.num+right.num+1;
        return new Element2(height,num);
    }
}
