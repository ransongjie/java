package com.xcrj.basic.pass2.tree;
/**
 * 折纸 从上到下输出折痕方向
 * 前序遍历构建，中序遍历（从上到下）输出
 */
public class PaperFolding {
    public static void main(String[] args) {
        int k=3;
        // 初始为1凹
        Node n=createInOrder(k, 1);
        Util.printTree(n);
        inOrder(n);
    }    

    //k代表层
    private static Node createInOrder(int k,int flag) {
        if(k<=0) return null;
        Node left=createInOrder(k-1,1);//1凹
        Node n=new Node(flag);
        Node right=createInOrder(k-1,0);//0凸
        n.left=left;
        n.right=right;
        return n;
    }

    private static void inOrder(Node n){
        if(n==null) return;
        inOrder(n.left);
        System.out.println(n.val==1?"凹":"凸");
        inOrder(n.right);
    }
}
