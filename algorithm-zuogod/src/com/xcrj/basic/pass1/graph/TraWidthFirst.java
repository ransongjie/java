package com.xcrj.basic.pass1.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TraWidthFirst {
    public static void main(String[] args) {
        int[][] inss={//有向图
            {1,2,0},
            {1,8,0},
            {2,3,0},
            {2,5,0},
            {3,4,0},
            {5,4,0},
            {8,6,0},
            {8,9,0},
            {6,4,0},
            {6,7,0}
        };
        Graph g=Convert.convert(inss);
        for(Edge e:g.edges){
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
        wfs(g);
    }

    public static void wfs(Graph g) {
        Queue<Node> que=new LinkedList<>();
        Set<Node> selected=new HashSet<>();
        que.offer(g.nodes.get(1));
        selected.add(g.nodes.get(1));
        while(!que.isEmpty()){
            Node a=que.poll();
            System.out.println(a.val);//出队访问
            for(Node b:a.nodes){
                if(selected.contains(b)) continue;
                que.offer(b);
                selected.add(b);
            }
        }
    }
}
