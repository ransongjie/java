package com.xcrj.basic.pass2.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
/**
 * 最小生成树
 * 加点法
 */
public class MSTPrim {
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
        Set<Edge> set = prim(g);
        for (Edge e : set) {
            System.out.println(e.from.val + ", " + e.to.val + ", " + e.weight);
        }
    }

    public static Set<Edge> prim(Graph g) {
        Set<Edge> rset = new HashSet<>();

        Set<Node> selected = new HashSet<>();// 选最小边相连的点
        //利用优先级队列，将已选点能到边排序
        PriorityQueue<Edge> pque = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        for (Node n : g.nodes.values()) {// 森林
            if (selected.contains(n)) continue;

            selected.add(n);
            for (Edge e : n.adjes) {
                pque.offer(e);
            }

            while (!pque.isEmpty()) {
                Edge e = pque.poll();
                if (selected.contains(e.to)) continue;

                rset.add(e);
                selected.add(e.to);
                //将新点连接边都加入队列
                for (Edge edge : e.to.adjes) {
                    pque.offer(edge);
                }
            }
        }

        return rset;
    }
}
