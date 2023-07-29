package com.xcrj.basic.pass1.tree;

import java.util.Stack;

//是否二叉搜索树
//中序遍历 升序
public class IsBinarySearchTree {
    public static void main(String[] args) {
        Node nodea=new Node(5);
        Node nodeb=new Node(2);
        Node nodec=new Node(8);
        Node noded=new Node(1);
        Node nodee=new Node(4);
        Node nodef=new Node(7);
        Node nodeg=new Node(9);
        Node nodeh=new Node(3);
        Node nodei=new Node(6);
        // Node nodei=new Node(1);
        //
        nodea.left=nodeb;nodea.right=nodec;
        nodeb.left=noded;nodeb.right=nodee;nodec.left=nodef;nodec.right=nodeg;
        noded.left=noded.right=null;nodee.left=nodeh;nodee.right=null;nodef.left=nodei;nodef.right=null;nodeg.left=nodeg.right=null;
        nodeh.left=nodeh.right=null;nodei.left=nodei.right=null;
        //
        Node root=nodea;
        // Util.printTree(root);
        //
        System.out.println(aSearchTree(root));
        System.out.println(aSearchTree2(root));
        System.out.println(aSearchTree3(root).aSearchTree);
    }

    static int preV=Integer.MIN_VALUE;//记录前1个值
    public static boolean aSearchTree(Node root) {
        if(root==null) return true;//空树是搜索二叉树
        if(!aSearchTree(root.left)){//左子树是不是搜索二叉树
            return false;
        }
        if(root.val<preV){
            return false;
        }else{
            preV=root.val;
        }
        return aSearchTree(root.right);//右边子树是不是搜索二叉树
    }

    public static boolean aSearchTree2(Node root) {
        if(root==null) return true;
        Stack<Node> s=new Stack<>();
        int preValue=Integer.MIN_VALUE;
        while(!s.isEmpty()||root!=null){
            if(root!=null){
                s.push(root);
                root=root.left;
            }else{
                root=s.pop();
                if(preValue>root.val) return false;
                else{
                    preValue=root.val;
                }
                root=root.right;
            }
        }
        return true;
    }

    /**
     * 后序遍历
     *
     * 左子树：
     * - 是不是搜索二叉树
     * - 左子树的最大值
     * 右子树
     * - 是不是搜索二叉树
     * - 右子树的最小值
     * 当前结点：
     * - 是不是搜索二叉树：值+左右子树
     * 
     * 信息结构
     * - bool 搜索二叉树
     * - int minVal
     * - int maxVal
     * 
     * @param root
     * @return
     */
    public static We aSearchTree3(Node root) {
        if(root==null) return null;//0个结点
        //左子树
        We left=aSearchTree3(root.left);
        //右子树
        We right=aSearchTree3(root.right);
        //当前结点
        int min=root.val;//xcrj 默认值 编程 1个结点
        int max=root.val;
        boolean aSearchTree=true;
        if(left!=null){
            min=Math.min(min, left.minVal);
            max=Math.max(max,left.maxVal);
        }
        if(right!=null){
            min=Math.min(min,right.minVal);
            max=Math.max(max,right.maxVal);
        }
        if(left!=null&&(!left.aSearchTree||left.maxVal>root.val)){
            aSearchTree=false;
        }
        if(right!=null&&(!right.aSearchTree||right.minVal<root.val)){
            aSearchTree=false;
        }
        return new We(aSearchTree,min,max);
    }

    static class We{
        boolean aSearchTree;
        int minVal;
        int maxVal;
        public We(){}
        public We(boolean ast,int minVal,int maxVal){
            this.aSearchTree=ast;
            this.minVal=minVal;
            this.maxVal=maxVal;
        }
    }
}
