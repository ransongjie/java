package com.xcrj.basic.pass1.union_set;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//并查集
public class UnionFind {
    public static void main(String[] args) {
        
    }

    static class Node{
        int v;
        public Node(int v){
            this.v=v;
        }
    }

    static Map<Integer,Node> vNode=new HashMap<>();
    static Map<Node,Node> nodeFather=new HashMap<>();
    //根节点所在集合元素数量 数量小的根节点挂到数量大的根节点下
    static Map<Node,Integer> rootNum=new HashMap<>();

    public static void initialize(int[] as) {
        if(as==null||as.length==0) return;
        for (int a : as) {
            Node node=new Node(a);
            nodeFather.put(node, node);
            rootNum.put(node, 1);
            vNode.put(a, node);
        }
    }

    public static boolean isSameSet(Node a,Node b) {
        return findRoot(a)==findRoot(b);
    }
    //合并两个集合
    public static void union(Node a,Node b){
        Node roota=findRoot(a);
        Node rootb=findRoot(b);
        if(roota==rootb) return;
        // int numa=rootNum.get(roota);
        // int numb=rootNum.get(rootb);
        // if(numa>=numb){
        //     nodeFather.put(rootb, roota);
        //     rootNum.put(roota, numa+numb);
        //     rootNum.remove(rootb);
        // }else{
        //     nodeFather.put(roota,rootb);
        //     rootNum.put(rootb, numa+numb);
        //     rootNum.remove(roota);
        // }
        //
        Node big=rootNum.get(roota)>=rootNum.get(rootb)?roota:rootb;
        Node small=big==roota?rootb:roota;
        nodeFather.put(small, big);
        rootNum.put(big,rootNum.get(roota)+rootNum.get(rootb));
        rootNum.remove(small);
    }

    //
    private static Node findRoot(Node node){
        if(node==null) return null;
        Stack<Node> stack=new Stack<>();
        stack.push(node);
        Node father=nodeFather.get(node);
        while(node!=father){//节点的父亲是自己
            node=father;
            father=findRoot(node);
            stack.push(node);
        }
        //优化
        while(!stack.isEmpty()){//
            Node n=stack.pop();
            nodeFather.put(n, node);
        }
        return node;
    }
}
