package com.xcrj.basic.pass3.union_set;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 */
public class UnionFind {
    static class Node{
        int v;
        public Node(int v){
            this.v=v;
        }
    }

    static Map<Integer,Node> vNode=new HashMap<>();
    static Map<Node,Node> nodefather=new HashMap<>();
    static Map<Node,Integer> rootNum=new HashMap<>();

    /**
     * 两结点是否在同一集合
     */
    public static boolean isSameSet(Node a,Node b) {
        return findRoot(a)==findRoot(b);
    }

    public static void initialize(int[]as) {
        if(as==null||as.length==0) return;
        for (int a : as) {
            Node n=new Node(a);
            vNode.put(a, n);
            nodefather.put(n, n);
            rootNum.put(n, 1);
        }
    }

    /**
     * 合并两个结点所在集合
     * @param a
     * @param b
     */
    public static void union(Node a,Node b) {
        if(a==null||b==null) return;
        Node aRoot=findRoot(a);Node bRoot=findRoot(b);
        if(aRoot==bRoot) return;
        Node bigRoot=rootNum.get(aRoot)>rootNum.get(bRoot)?aRoot:bRoot;
        Node smlRoot=bigRoot==aRoot?bRoot:aRoot;
        nodefather.put(smlRoot, bigRoot);
        rootNum.put(bigRoot, rootNum.get(aRoot)+rootNum.get(bRoot));
        rootNum.remove(smlRoot);
    }

    /**
     * 寻找结点的根结点
     * @param n
     * @return
     */
    private static Node findRoot(Node n) {
        if(n==null) return null;
        Stack<Node> stack=new Stack<>();
        stack.push(n);
        Node father=nodefather.get(n);
        //n等于father n是根节点
        while(n!=father){//找n的father，找father的father
            n=father;
            father=findRoot(n);
            stack.push(n);
        }

        //优化
        while(!stack.isEmpty()){
            nodefather.put(stack.pop(), n);
        }

        return n;
    }
}
