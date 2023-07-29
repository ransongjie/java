package com.xcrj.basic.pass1.tree;

/**
 * 折纸 从上到下输出折痕方向
 * 前序遍历构建，中序遍历（从上到下）输出
 */
public class PaperFolding {
    public static void main(String[] args) {
        Node root=buildPreOrder(1,3);
        Util.printTree(root);
        //1方法 先建立树再遍历打印树
        // String str=printInOrder(root);
        // System.out.println(str);
        //2方法 直接打印树
        printInOrder2(root,true, 3);
    }

    /**
     * 根是凹的，左孩子始终是凹的，右孩子始终是凸的
     * @param a 1 left 凹，-1 right 凸
     * @param k
     * @return
     */
    public static Node buildPreOrder(int a,int k) {
        if(k==0) return null;
        Node node=new Node(a);
        node.left=buildPreOrder(1,k-1);
        node.right=buildPreOrder(-1,k-1);
        return node;
    }

    public static String printInOrder(Node root) {
        if(root==null) return "";
        String str="";
        str+=printInOrder(root.left);
        str+=root.val==1?"凹":"凸";
        str+=printInOrder(root.right);
        return str;
    }

    public static void printInOrder2(Node root,boolean a,int k) {
        if(k==0) return;
        printInOrder2(root.left,true,k-1);
        System.out.print(a?"凹":"凸");
        printInOrder2(root, false, k-1);
    }
}
