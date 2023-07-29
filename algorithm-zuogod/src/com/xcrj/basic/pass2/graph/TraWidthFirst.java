package com.xcrj.basic.pass2.graph;

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
        Graph g=Convert.toGraph(inss);
        for(Edge e:g.edges){
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
        wfs(g);
    }

    public static void wfs(Graph g) {
        if(g==null) return;
        Queue<Node> que=new LinkedList<>();
        Set<Node> visited=new HashSet<>();
        que.offer(g.nodes.get(1));//随便找一个点开始遍历
        visited.add(g.nodes.get(1));
        while(!que.isEmpty()){
            Node a=que.poll();
            System.out.println(a.val);
            for(Node b:a.adjns){
                if(visited.contains(b)) continue;
                que.offer(b);
                visited.add(b);
            }
        }
    }
}