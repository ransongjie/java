package com.xcrj.basic.pass2.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树
 * 避圈法
 * 简单并查集
 */
public class MSTKruskal {
    public static void main(String[] args) {
        int[][] inss = {
                { 1, 2, 6 },
                { 1, 3, 1 },
                { 1, 4, 5 },
                { 2, 3, 5 },
                { 3, 4, 5 },
                { 2, 5, 3 },
                { 3, 5, 6 },
                { 3, 6, 4 },
                { 5, 6, 6 },
                { 4, 6, 2 },
                { 3, 4, 5 },
                { 2, 1, 6 },
                { 3, 1, 1 },
                { 4, 1, 5 },
                { 3, 2, 5 },
                { 4, 3, 5 },
                { 5, 2, 3 },
                { 5, 3, 6 },
                { 6, 3, 4 },
                { 6, 5, 6 },
                { 6, 4, 2 },
                { 4, 3, 5 },
        };

        Graph g = Convert.toGraph(inss);
        Set<Edge> set = kruskal(g);
        for (Edge e : set) {
            System.out.println(e.from.val + ", " + e.to.val + ", " + e.weight);
        }
    }

    /**
     * 并查集
     */
    static class MySet {
        //Map<点，所在集合>
        Map<Node, List<Node>> nodeList;

        public MySet() {
            this.nodeList = new HashMap<>();
        }
        //两个点在同一个集合
        public boolean isSameSet(Node a, Node b) {
            return nodeList.get(a) == nodeList.get(b);
        }
        //合并两个点的集合
        public void union(Node a, Node b) {
            if (isSameSet(a, b))
                return;
            List<Node> aList = nodeList.get(a);
            List<Node> bList = nodeList.get(b);
            for (Node node : aList) {
                bList.add(node);
                nodeList.put(node, bList);
            }
        }
    }

    public static Set<Edge> kruskal(Graph g) {
        Set<Edge> set=new HashSet<>();

        //初始化并查集
        MySet mySet = new MySet();
        for (Node n : g.nodes.values()) {
            List<Node> list = new ArrayList<>();
            list.add(n);
            mySet.nodeList.put(n, list);
        }

        //根据权重排序边
        PriorityQueue<Edge> pque=new PriorityQueue<>((e1,e2)->e1.weight-e2.weight);
        for (Edge e : g.edges) {
            pque.offer(e);
        }

        //根据权重从小到大选边，利用并查集避开圈
        while(!pque.isEmpty()){
            Edge e=pque.poll();
            Node a = e.from;
            Node b = e.to;
            if (mySet.isSameSet(a, b))continue;
            set.add(e);
            mySet.union(a, b);
        }

        return set;
    }
}
