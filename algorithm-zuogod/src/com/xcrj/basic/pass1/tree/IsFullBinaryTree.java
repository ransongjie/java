package com.xcrj.basic.pass1.tree;
//满二叉树
public class IsFullBinaryTree {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        node1.left=node2;node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;
        node3.right=node7;
        Node root=node1;
        // Util.printTree(root);
        //
        // System.out.println(fbt(root).aFBT);
        We2 we2=fbt2(root);
        System.out.println((1<<we2.height)-1==we2.nodeNum);
    }

    /**
     * 后续遍历
     * 结点数量=2^height-1
     * 左子树：
     * - height
     * - nodeNum
     * 右子树
     * - height
     * - nodeNum
     * 当前结点
     * - height
     * - nodeNum
     * @param root
     */
    public static We fbt(Node root) {
        if(root==null) return null;
        //左子树
        We left=fbt(root.left);
        //右子树
        We right=fbt(root.right);
        //当前结点
        boolean aFBT=true;
        int level=1;
        int nodeNum=1;
        if(left!=null){
            nodeNum+=left.nodeNum;
            level=Math.max(level, left.level+1);
        }
        if(right!=null){
            nodeNum+=right.nodeNum;
            level=Math.max(level,right.level+1);
        }
        if(nodeNum!=(1<<level)-1){
            aFBT=false;
        }
        return new We(aFBT,level,nodeNum);
    }

    static class We{
        boolean aFBT;
        int level;
        int nodeNum;
        public We(){}
        public We(boolean aFBT, int level, int nodeNum){
            this.aFBT=aFBT;
            this.level=level;
            this.nodeNum=nodeNum;
        }
    }

    public static We2 fbt2(Node root) {
        if(root==null) return new We2(0,0);//
        //左子树
        We2 left=fbt2(root.left);
        //右子树
        We2 right=fbt2(root.right);
        //当前结点
        int height=Math.max(left.height, right.height)+1;
        int nodeNum=left.nodeNum+right.nodeNum+1;
        return new We2(height,nodeNum);
    }

    static class We2{
        int height;
        int nodeNum;
        public We2(){}
        public We2(int height, int nodeNum){
            this.height=height;
            this.nodeNum=nodeNum;
        }
    }
}
