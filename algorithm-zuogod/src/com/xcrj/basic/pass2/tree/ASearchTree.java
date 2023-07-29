package com.xcrj.basic.pass2.tree;

import java.util.Stack;

public class ASearchTree {
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
        Util.printTree(root);
        //
        System.out.println(aSearchTree(root));
        System.out.println(aSearchTree2(root));
        System.out.println(aSearchTree3(root));
    }

    public static boolean aSearchTree(Node r) {
        if(r==null) return false;
        Element e=process(r);
        return e.isSearchTree;
    }
    static class Element{
        int min;
        int max;
        boolean isSearchTree;
        public Element(){}
        public Element(int min,int max,boolean isSearchTree){
            this.min=min;
            this.max=max;
            this.isSearchTree=isSearchTree;
        }
    }
    public static Element process(Node r) {
        if(r==null) return null;
        
        //当前结点
        int min=r.val;
        int max=r.val;
        boolean isSearchTree=true;
        //左右子树
        Element le=process(r.left);
        Element re=process(r.right);
        //
        if(le!=null){
            min=Math.min(min, le.min);
            max=Math.max(max, le.max);
        }
        if(re!=null){
            min=Math.min(min, re.min);
            max=Math.max(max, re.max);
        }
        if(le!=null&&(!le.isSearchTree||le.max>r.val)){
            isSearchTree=false;
        }
        if(re!=null&&(!re.isSearchTree||re.min<r.val)){
            isSearchTree=true;
        }
        Element ce=new Element(min,max,isSearchTree);
        return ce;
    }

    public static boolean aSearchTree2(Node n) {
        return proccess2(n,Integer.MIN_VALUE);//
    }

    public static boolean proccess2(Node n,int preV) {
        if(n==null) return true;//

        if(!proccess2(n.left,preV)) return false;
        if(preV>n.val) return false;
        else preV=n.val;//
        return proccess2(n.right,preV);
    }

    public static boolean aSearchTree3(Node n) {
        if(n==null) return false;
        Stack<Node> s=new Stack<>();
        Node p=n;
        int preV=Integer.MIN_VALUE;
        while(!s.isEmpty()||p!=null){
            if(p!=null){
                s.push(p);
                p=p.left;
            }else{
                p=s.pop();
                if(preV>p.val) return false;
                else preV=p.val;
                p=p.right;
            }
        }

        return true;
    }
}
