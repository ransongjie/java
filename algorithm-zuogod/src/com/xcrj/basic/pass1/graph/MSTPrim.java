package com.xcrj.basic.pass1.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

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

        Graph g = Convert.convert(inss);
        Set<Edge> set = prim(g);
        for (Edge e : set) {
            System.out.println(e.from.val + ", " + e.to.val + ", " + e.weight);
        }
    }

    public static Set<Edge> prim(Graph g) {
        Set<Edge> rset=new HashSet<>();
        //
        Set<Node> selected=new HashSet<>();
        PriorityQueue<Edge> pque= new PriorityQueue<>((o1,o2)->o1.weight-o2.weight);
        for(Node n:g.nodes.values()){//若是联通图可以不要这个
            if(selected.contains(n)) continue;
            //起点 边们
            selected.add(n);
            for(Edge e:n.edges){
                pque.offer(e);
            }
            //
            while(!pque.isEmpty()){
                Edge e=pque.poll();
                Node b=e.to;
                if(selected.contains(b)) continue;//
                rset.add(e);
                //新点 边们
                selected.add(b);
                for(Edge edge:b.edges){
                    pque.offer(edge);
                }
            }
        }
        //
        return rset;
    }
}
