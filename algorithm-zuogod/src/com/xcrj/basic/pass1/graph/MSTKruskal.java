package com.xcrj.basic.pass1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//无向图 简化版并查集
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

        Graph g=Convert.convert(inss);
        Set<Edge>set=kruskal(g);
        for(Edge e:set){
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
    }

    static class MySets {// 简化版并查集
        Map<Node, List<Node>> setMap;

        public MySets() {
            this.setMap = new HashMap<>();
        }

        boolean aSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        void union(Node from, Node to) {
            // from集合 和 to集合 中的所有 node 指向同一个 List
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node n : toSet) {
                fromSet.add(n);
                setMap.put(n, fromSet);//
            }
        }
    }

    public static Set<Edge> kruskal(Graph g) {
        Set<Edge> rset = new HashSet<>();
        //
        MySets mySets = new MySets();
        for (Node n : g.nodes.values()) {
            List<Node> list = new ArrayList<>();
            list.add(n);
            mySets.setMap.put(n, list);
        }
        //
        PriorityQueue<Edge> pque = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);//
        for (Edge e : g.edges) {
            pque.offer(e);
        }
        // 选小边 避开环
        while (!pque.isEmpty()) {
            Edge e = pque.poll();
            if (!mySets.aSameSet(e.from, e.to)) {
                rset.add(e);
                mySets.union(e.from, e.to);
            }
        }
        //
        return rset;
    }
}
